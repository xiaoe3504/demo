package com.psy.demo.service.impl;

import static org.apache.http.HttpHeaders.ACCEPT;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.apache.http.HttpHeaders.AUTHORIZATION;
import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.psy.demo.enums.PayAttachTypeEnum;
import com.psy.demo.global.BaseException;
import com.psy.demo.service.HttpClientService;
import com.psy.demo.utils.*;


import java.io.*;
import java.net.URISyntaxException;

import com.psy.demo.vo.req.Attach;
import com.psy.demo.vo.req.PayReq;
import com.psy.demo.vo.res.PayRes;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpHost;
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

    private static final HttpHost proxy = null;

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
    public PayRes dealPay(PayReq payReq) {
        PayRes res;
        String prePayIdString;

        int amount = payReq.getAmount();
        String tradeNo = System.currentTimeMillis() + CommonUtils.generateNonceStr();
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
            log.info("token:" + token);
            //拼装http头的Authorization内容
            httpPost.addHeader(AUTHORIZATION, token);

            CloseableHttpResponse response = httpClient.execute(httpPost);

            prePayIdString = EntityUtils.toString(response.getEntity());
            res = getRes(prePayIdString);
            log.info("prePayIdString:" + prePayIdString);
        } catch (IOException e) {
            log.error("dealPost err:" + e.getMessage(), e);
            throw new BaseException("dealPost err:");
        }
        return res;

    }

    private PayRes getRes(String prePayIdString) {
        PayRes res = new PayRes();
        JSONObject resJson = JSON.parseObject(prePayIdString, JSONObject.class);
        String prepayId = resJson.getString("prepay_id");
        res.setPrepayId(prepayId);
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
                + "\"appid\": \"" + appId + "\"" + "}";
    }

    @Override
    public String dealGet() {
        HttpGet httpGet = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(MyConstantString.CER_URL);
            httpGet = new HttpGet(uriBuilder.build());
        } catch (URISyntaxException e) {
            log.error(e.getMessage(), e);
            throw new BaseException("dealGet error");
        }
        httpGet.addHeader(ACCEPT, APPLICATION_JSON.toString());
        httpGet.addHeader(CONTENT_TYPE, APPLICATION_JSON.toString());
        HttpUrl httpurl = HttpUrl.parse(MyConstantString.CER_URL);
        String token = GetTokenUtils.getToken(HttpGet.METHOD_NAME, httpurl, null);
        //拼装http头的Authorization内容
        httpGet.addHeader(AUTHORIZATION, token);
        CloseableHttpResponse response = null;
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
        try {
            HttpEntity entity = response.getEntity();
            // do something useful with the response body
            // and ensure it is fully consumed
            log.info(entity.toString());
            EntityUtils.consume(entity);
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
        return null;
    }

}
