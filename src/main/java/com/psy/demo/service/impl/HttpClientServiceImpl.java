package com.psy.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gson.JsonObject;
import com.psy.demo.global.BaseException;
import com.psy.demo.service.HttpClientService;
import com.psy.demo.utils.*;


import java.io.*;
import java.util.concurrent.TimeUnit;

import com.psy.demo.utils.coze.JWTUtils;
import com.psy.demo.vo.req.MyProfitSharingQueryReq;
import com.psy.demo.vo.req.MyProfitSharingReq;
import com.psy.demo.vo.req.PayReq;
import com.psy.demo.vo.res.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jetbrains.annotations.NotNull;
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
        int amount = payReq.getAmount();
        String tradeNo = System.currentTimeMillis() + "_" + CommonUtils.generateNonceStr();
        // 请求body参数
        String body = genBody(payReq, amount, tradeNo);
        String token = GetTokenUtils.getToken(HttpPost.METHOD_NAME, HttpUrl.parse(MyConstantString.JSAPI_URL), body);
        JSONObject json = HttpUtils.dealHttpPost(httpClient, MyConstantString.JSAPI_URL, token, body, JSONObject.class);
        String prePayIdString = json.getString("prepay_id");
        PayRes res = getRes(amount, tradeNo, prePayIdString);
        log.info("prePayId res:" + JSONObject.toJSONString(res));
        return res;

    }


    private PayRes getRes(int amount, String tradeNo, String prepayId) {
        PayRes res = new PayRes();
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
        String token = GetTokenUtils.getToken(HttpGet.METHOD_NAME, HttpUrl.parse(MyConstantString.CER_URL), "");
        return HttpUtils.dealHttpGet
                (httpClient, MyConstantString.CER_URL, token, WeChatCerRes.class);
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
        String body = "{\"appid\":\"" + MyConstantString.APPID + "\"," +
                "\"transaction_id\":\"" + req.getTransactionId() + "\"," +
                "\"out_order_no\":\"" + req.getTransactionId() + "_" + RandomUtils.genRandomLetters(6) + "\"," +
                "\"receivers\":[{\"type\":\"PERSONAL_OPENID\"," +
                "\"account\":\"" + req.getOpenId() + "\"," +
                "\"amount\":" + req.getAmount() +
                ",\"description\":\"分账给咨询师\"}" +
                "]," +
                "\"unfreeze_unsplit\":false}";
        ProfitSharingDealRes profitSharingDealRes = HttpUtils.dealHttpPost
                (httpClient, dealUrl, null, body, ProfitSharingDealRes.class);
        if (profitSharingDealRes != null && StringUtils.isNotEmpty(profitSharingDealRes.getOrder_id())) {
            log.info("dealProfitSharing suc :" + JSONObject.toJSONString(profitSharingDealRes));
        }
        return profitSharingDealRes;
    }

    @Override
    public String queryTransactionId(String tradeNo) {
        String url = MyConstantString.QUERY_TRANSACTION_ID_URL + tradeNo + "?mchid=" + MyConstantString.MERCHANT_ID;
        HttpUrl httpurl = HttpUrl.parse(url);
        String token = GetTokenUtils.getToken(HttpGet.METHOD_NAME, httpurl, null);
        QueryTransactionIdRes res = HttpUtils.dealHttpGet(httpClient, url, token, QueryTransactionIdRes.class);
        log.info("queryTransactionId res:" + JSONObject.toJSONString(res));
        return res.getTransaction_id();

    }

    @Override
    public JsonObject queryProfitSharing(MyProfitSharingQueryReq req, String queryUrl) {


        return null;
    }

    @Override
    public CozeAccessTokenRes getAccessToken() {
        String token = MyConstantString.TOKEN_SUFFIX + JWTUtils.getJWT();
        return HttpUtils.dealHttpPost(httpClient, MyConstantString.TOKEN_URL_COZE, token, MyConstantString.COZE_AUTH_BODY, CozeAccessTokenRes.class);
    }

    @Override
    public String createConversation(String cozeAccessToken) {
        CozeConversationAddRes res = HttpUtils.dealHttpGet
                (httpClient, MyConstantString.CONVERSATION_CREATE_URL_COZE, cozeAccessToken, CozeConversationAddRes.class);
        if (res != null && res.getData() != null) {
            return res.getData().getId();
        } else {
            throw new BaseException("createConversation error res null");
        }
    }

    @Override
    public CozeBootRes chat(String conversationId, String openId, String content,
                       String cozeAccessToken, String bootId) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n").append("    \"bot_id\": \"")
                .append(bootId).append("\",\n")
                .append("    \"user_id\": \"")
                .append(openId).append("\",\n")
                .append("    \"stream\": true,\n")
                .append("    \"auto_save_history\":true,\n")
                .append("    \"additional_messages\":[\n");

        sb.append("        {\n")
                .append("            \"role\":\"user\",\n")
                .append("            \"content\":\"")
                .append(content)
                .append("\",\n")
                .append("            \"content_type\":\"text\"\n")
                .append("        }\n");

        sb.append("    ]\n")
                .append("}");

        String body = sb.toString();

        String url = MyConstantString.CHAT_URL_COZE + "?conversation_id=" + conversationId;

        return HttpUtils.dealCozeHttpPostStream(httpClient, url, cozeAccessToken, body);
    }


    @Override
    public ProfitSharingAddRes addProfitSharing(String openId, String addUrl) {
        HttpUrl httpUrl = HttpUrl.parse(addUrl);
        String body = "{\"appid\":\"" + MyConstantString.APPID +
                "\",\"type\":\"PERSONAL_OPENID\",\"account\":\"" +
                openId +
                "\",\"relation_type\":\"PARTNER\"}";
        String token = GetTokenUtils.getToken(HttpPost.METHOD_NAME, httpUrl, body);
        ProfitSharingAddRes profitSharingAddRes = HttpUtils.dealHttpPost(httpClient, addUrl, token, body,
                ProfitSharingAddRes.class);
        log.info("add profitSharing string res :" + JSONObject.toJSONString(profitSharingAddRes));
        if (profitSharingAddRes != null && StringUtils.isNotEmpty(profitSharingAddRes.getAccount())) {
            log.info("add profitSharing suc :" + JSONObject.toJSONString(profitSharingAddRes));
        }
        return profitSharingAddRes;
    }


}
