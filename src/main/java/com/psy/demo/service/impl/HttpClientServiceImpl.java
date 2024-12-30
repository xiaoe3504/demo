package com.psy.demo.service.impl;

import static org.apache.http.HttpHeaders.ACCEPT;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.apache.http.HttpHeaders.AUTHORIZATION;
import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gson.JsonObject;
import com.psy.demo.global.BaseException;
import com.psy.demo.service.HttpClientService;
import com.psy.demo.utils.*;


import java.io.*;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.psy.demo.vo.req.MyProfitSharingQueryReq;
import com.psy.demo.vo.req.MyProfitSharingReq;
import com.psy.demo.vo.req.PayReq;
import com.psy.demo.vo.res.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
@Slf4j
public class HttpClientServiceImpl implements HttpClientService {
    // 你的商户私钥
    private static final String merchantId = MyConstantString.MERCHANT_ID; // 商户号
    private static final String appId = MyConstantString.APPID; // 商户号
    private CloseableHttpClient httpClient;

    private final LoadingCache<String, WeChatCerRes> cache = CacheBuilder.newBuilder()
            .maximumSize(10) // 最多缓存100个键值对
            .expireAfterWrite(10, TimeUnit.DAYS) // 写入后10天过期
            .build(
                    new CacheLoader<String, WeChatCerRes>() {
                        @NotNull
                        public WeChatCerRes load(@NotNull String key) {
                            if (key.equals(MyConstantString.CER_TEXT)) {
                                return dealCer();
                            }
                            return null;
                        }
                    }
            );

    @PostConstruct
    public void init() {
        // 构造httpclient
        httpClient = HttpClients.createDefault();
    }

    @PreDestroy
    public void after() throws IOException {
        httpClient.close();
    }

    @Override
    public PayRes dealPrepay(PayReq payReq) {
        PayRes res;
        String prePayIdString;

        int amount = payReq.getAmount();
        String tradeNo = System.currentTimeMillis() + "_" + CommonUtils.generateNonceStr();
        try {
            HttpPost httpPost = new HttpPost(MyConstantString.JSAPI_URL);
            httpPost.addHeader(ACCEPT, APPLICATION_JSON.toString());
            httpPost.addHeader(CONTENT_TYPE, APPLICATION_JSON.toString());
            // 请求body参数
            String body = genBody(payReq, amount, tradeNo);
            log.info("req body:" + body);
            StringEntity entity = new StringEntity(body, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);

            HttpUrl httpurl = HttpUrl.parse(MyConstantString.JSAPI_URL);
            String token = GetTokenUtils.getToken(HttpPost.METHOD_NAME, httpurl, body);
            //拼装http头的Authorization内容
            httpPost.addHeader(AUTHORIZATION, token);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            prePayIdString = EntityUtils.toString(response.getEntity());

            res = getRes(amount, tradeNo, prePayIdString);
            log.info("prePayId res:" + JSONObject.toJSONString(res));
        } catch (IOException e) {
            log.error("dealPost err:" + e.getMessage(), e);
            throw new BaseException("dealPost err:");
        }
        return res;

    }


    private PayRes getRes(int amount, String tradeNo, String prePayIdString) {
        PayRes res = new PayRes();
        JSONObject resJson = JSON.parseObject(prePayIdString, JSONObject.class);
        String prepayId = resJson.getString("prepay_id");
        res.setPrepayId(prepayId);
        res.setAmount(String.valueOf(amount));
        res.setTradeNo(tradeNo);
        return res;
    }

    @NotNull
    private String genBody(PayReq payReq, int amount, String tradeNo) {
        String openId = payReq.getOpenId();
        String description = payReq.getDescription();
        String attach = payReq.getAttach();
        return "{"
                + "\"amount\": {"
                + "\"total\": " + amount + ","
                + "\"currency\": \"CNY\""
                + "},"
                + "\"mchid\": \"" + merchantId + "\","
                + "\"attach\": \"" + attach + "\","
                + "\"description\": \"" + description + "\","
                + "\"notify_url\": \"https://www.weixin.qq.com/wxpay/pay.php\","
                + "\"payer\": {"
                + "\"openid\": \"" + openId + "\"" + "},"
                + "\"out_trade_no\": \"" + tradeNo + "\","
                + "\"goods_tag\": \"WXG\","
                + "\"appid\": \"" + appId + "\""
                + ",\"settle_info\":{\"profit_sharing\":true}" +
                "}";
    }

    @Override
    public WeChatCerRes dealCer() {
        WeChatCerRes res;
        HttpGet httpGet;
        try {
            URIBuilder uriBuilder = new URIBuilder(MyConstantString.CER_URL);
            httpGet = new HttpGet(uriBuilder.build());
        } catch (URISyntaxException e) {
            log.error(e.getMessage(), e);
            throw new BaseException("dealGet error");
        }

        HttpUrl httpurl = HttpUrl.parse(MyConstantString.CER_URL);
        String token = GetTokenUtils.getToken(HttpGet.METHOD_NAME, httpurl, "");
        //拼装http头的Authorization内容
        CloseableHttpResponse response = dealHttpGet(token, httpGet);
        if (response == null) {
            return null;
        }
        try {
            // do something useful with the response body
            // and ensure it is fully consumed
            String resString = EntityUtils.toString(response.getEntity());
            res = JSONObject.parseObject(resString, WeChatCerRes.class);
            log.info("cer res:" + res);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new BaseException("dealGet error");
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                throw new BaseException("dealGet error");
            }
        }
        return res;
    }

    @SneakyThrows
    @Override
    public WeChatCerRes getCerText() {
        return cache.get(MyConstantString.CER_TEXT);
    }

    @Override
    public BaseRes dealCallback(PayCallbackRes payCallbackRes) {
        log.info("payCallbackRes:" + JSONObject.toJSONString(payCallbackRes));
        return BaseRes.ofSuccess("payCallbackRes suc");
    }


    @Override
    public ProfitSharingDealRes dealProfitSharing(MyProfitSharingReq req, String dealUrl) {
        HttpUrl httpUrl = HttpUrl.parse(dealUrl);
        //todo unfreeze_unsplit 改成true
        String body = "{\"appid\":\"" + MyConstantString.APPID + "\"," +
                "\"transaction_id\":\"" + req.getTransactionId() + "\"," +
                "\"out_order_no\":\"" + req.getTransactionId() +"_"+ RandomUtils.genRandomLetters(6) + "\"," +
                "\"receivers\":[{\"type\":\"PERSONAL_OPENID\"," +
                "\"account\":\"" + req.getOpenId() + "\"," +
                "\"amount\":" + req.getAmount() +
                ",\"description\":\"分账给咨询师\"}" +
                "]," +
                "\"unfreeze_unsplit\":false}";
        HttpPost httpPost = getHttpPost(dealUrl, httpUrl, body);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            String res = EntityUtils.toString(response.getEntity());
            log.info("dealProfitSharing string res :" + res);
            ProfitSharingDealRes profitSharingDealRes = JSONObject.parseObject(res, ProfitSharingDealRes.class);
            if (profitSharingDealRes != null && StringUtils.isNotEmpty(profitSharingDealRes.getOrder_id())) {
                log.info("dealProfitSharing suc :" + JSONObject.toJSONString(profitSharingDealRes));
            }
            return profitSharingDealRes;
        } catch (IOException e) {
            log.error("dealProfitSharing err:" + e.getMessage(), e);
            throw new BaseException("add profitSharing err:");
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                throw new BaseException("dealGet error");
            }
        }
    }

    @Override
    public String queryTransactionId(String tradeNo) {
        QueryTransactionIdRes res;
        String url = MyConstantString.QUERY_TRANSACTION_ID_URL + tradeNo + "?mchid=" + MyConstantString.MERCHANT_ID;
        HttpUrl httpurl = HttpUrl.parse(url);
        String token = GetTokenUtils.getToken(HttpGet.METHOD_NAME, httpurl, null);
        HttpGet httpGet;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            httpGet = new HttpGet(uriBuilder.build());
        } catch (URISyntaxException e) {
            log.error(e.getMessage(), e);
            throw new BaseException("dealGet error");
        }
        CloseableHttpResponse response = dealHttpGet(token, httpGet);
        if (response == null) {
            return null;
        }
        try {
            // do something useful with the response body
            // and ensure it is fully consumed
            String resString = EntityUtils.toString(response.getEntity());
            res = JSONObject.parseObject(resString, QueryTransactionIdRes.class);
            log.info("queryTransactionId res:" + JSONObject.toJSONString(res));
            return res.getTransaction_id();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new BaseException("queryTransactionId error");
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                throw new BaseException("queryTransactionId error");
            }
        }
    }

    @Override
    public JsonObject queryProfitSharing(MyProfitSharingQueryReq req, String queryUrl) {


        return null;
    }

    @Nullable
    private CloseableHttpResponse dealHttpGet(String token, HttpGet httpGet) {
        httpGet.addHeader(ACCEPT, APPLICATION_JSON.toString());
        httpGet.addHeader(CONTENT_TYPE, APPLICATION_JSON.toString());
        //拼装http头的Authorization内容
        httpGet.addHeader(AUTHORIZATION, token);
        CloseableHttpResponse response;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new BaseException("dealGet error");
        }
        if (SC_OK != response.getStatusLine().getStatusCode()) {
            log.error("res fail...");
            return null;
        }
        return response;
    }

    @NotNull
    private HttpPost getHttpPost(String dealUrl, HttpUrl httpUrl, String body) {
        String token = GetTokenUtils.getToken(HttpPost.METHOD_NAME, httpUrl, body);
        HttpPost httpPost = new HttpPost(dealUrl);
        httpPost.addHeader(ACCEPT, APPLICATION_JSON.toString());
        httpPost.addHeader(CONTENT_TYPE, APPLICATION_JSON.toString());
        StringEntity entity = new StringEntity(body, ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);
        httpPost.addHeader(AUTHORIZATION, token);
        return httpPost;
    }

    @Override
    public ProfitSharingAddRes addProfitSharing(String openId, String addUrl) {
        HttpUrl httpUrl = HttpUrl.parse(addUrl);
        String body = "{\"appid\":\"" + MyConstantString.APPID +
                "\",\"type\":\"PERSONAL_OPENID\",\"account\":\"" +
                openId +
                "\",\"relation_type\":\"PARTNER\"}";
        HttpPost httpPost = getHttpPost(addUrl, httpUrl, body);
        try {
            CloseableHttpResponse response = httpClient.execute(httpPost);
            String res = EntityUtils.toString(response.getEntity());
            log.info("add profitSharing string res :" + res);
            ProfitSharingAddRes profitSharingAddRes = JSONObject.parseObject(res, ProfitSharingAddRes.class);
            if (profitSharingAddRes != null && StringUtils.isNotEmpty(profitSharingAddRes.getAccount())) {
                log.info("add profitSharing suc :" + JSONObject.toJSONString(profitSharingAddRes));
            }
            return profitSharingAddRes;
        } catch (IOException e) {
            log.error("add profitSharing err:" + e.getMessage(), e);
            throw new BaseException("add profitSharing err:");
        }
    }


}
