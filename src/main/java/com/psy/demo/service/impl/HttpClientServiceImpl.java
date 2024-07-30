package com.psy.demo.service.impl;

import static org.apache.http.HttpHeaders.ACCEPT;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.apache.http.HttpHeaders.AUTHORIZATION;
import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

import com.psy.demo.global.BaseException;
import com.psy.demo.service.HttpClientService;
import com.psy.demo.utils.*;
import com.psy.demo.utils.auth.PrivateKeySigner;
import com.psy.demo.utils.auth.Verifier;
import com.psy.demo.utils.auth.WechatPay2Credentials;
import com.psy.demo.utils.auth.WechatPay2Validator;
import com.psy.demo.utils.cert.CertificatesManager;


import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;

import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpHost;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
@Slf4j
public class HttpClientServiceImpl implements HttpClientService {

    // 你的商户私钥
    private static final String privateKey = MyConstantString.PRIVATE_KEY;
    private static final String merchantId = MyConstantString.MERCHANT_ID; // 商户号
    private static final String appId = MyConstantString.APPID; // 商户号
    private static final String merchantSerialNumber = MyConstantString.MERCHANT_SERIAL_NUMBER; // 商户证书序列号
    private static final String apiV3Key = MyConstantString.API_V3_KEY; // API V3密钥
    private CloseableHttpClient httpClient;
    CertificatesManager certificatesManager;
    Verifier verifier;

    private static final HttpHost proxy = null;

    @PostConstruct
    public void init() throws Exception {
        PrivateKey merchantPrivateKey = PemUtil.loadPrivateKey(privateKey);
        // 获取证书管理器实例
        certificatesManager = CertificatesManager.getInstance();
        // 添加代理服务器
        certificatesManager.setProxy(proxy);
        // 向证书管理器增加需要自动更新平台证书的商户信息
        certificatesManager.putMerchant(merchantId, new WechatPay2Credentials(merchantId,
                        new PrivateKeySigner(merchantSerialNumber, merchantPrivateKey)),
                apiV3Key.getBytes(StandardCharsets.UTF_8));
        // 从证书管理器中获取verifier
        verifier = certificatesManager.getVerifier(merchantId);
        // 构造httpclient
        httpClient = WechatPayHttpClientBuilder.create()
                .withMerchant(merchantId, merchantSerialNumber, merchantPrivateKey)
                .withValidator(new WechatPay2Validator(verifier))
                .build();
    }

    @PreDestroy
    public void after() throws IOException {
        httpClient.close();
    }

    @Override
    public String dealPost(String openId) {
        String bodyAsString;
        try {
            HttpPost httpPost = new HttpPost(MyConstantString.JSAPI_URL);
            httpPost.addHeader(ACCEPT, APPLICATION_JSON.toString());
            httpPost.addHeader(CONTENT_TYPE, APPLICATION_JSON.toString());
            // 请求body参数
            String body = "{"
                    + "\"amount\": {"
                    + "\"total\": 1,"
                    + "\"currency\": \"CNY\""
                    + "},"
                    + "\"mchid\": \"" + merchantId + "\","
                    + "\"description\": \"Image形象店-深圳腾大-QQ公仔\","
                    + "\"notify_url\": \"https://www.weixin.qq.com/wxpay/pay.php\","
                    + "\"payer\": {"
                    + "\"openid\": \"" + openId + "\"" + "},"
                    + "\"out_trade_no\": \"2024072912345678901234567890\","
                    + "\"goods_tag\": \"WXG\","
                    + "\"appid\": \"" + appId + "\"" + "}";
            log.info("req body:" + body);
            StringEntity entity = new StringEntity(body, "utf-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);

            HttpUrl httpurl = HttpUrl.parse(MyConstantString.JSAPI_URL);
            String token = GetTokenUtils.getToken(HttpPost.METHOD_NAME, httpurl, body);
            log.info("token:" + token);
            //拼装http头的Authorization内容
            httpPost.addHeader(AUTHORIZATION, token);

            CloseableHttpResponse response = httpClient.execute(httpPost);

            bodyAsString = EntityUtils.toString(response.getEntity());
            log.info("bodyAsString:" + bodyAsString);
        } catch (IOException e) {
            log.error("dealPost err:" + e.getMessage(), e);
            throw new BaseException("dealPost err:");
        }
        return bodyAsString;

    }

    @Override
    public String dealGet() {
        HttpGet httpGet = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(MyConstantString.CER_URL);
            httpGet = new HttpGet(uriBuilder.build());
        } catch (URISyntaxException e) {
            log.error(e.getMessage(),e);
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
            log.error(e.getMessage(),e);
            throw new BaseException("dealGet error");
        }
        if (SC_OK != response.getStatusLine().getStatusCode()) {
            log.error("res fail...");
            return null;
        }
        ;
        try {
            HttpEntity entity = response.getEntity();
            // do something useful with the response body
            // and ensure it is fully consumed
            log.info(entity.toString());
            EntityUtils.consume(entity);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
            throw new BaseException("dealGet error");
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                log.error(e.getMessage(),e);
                throw new BaseException("dealGet error");
            }
        }
        return null;
    }


    public void uploadImageTest() throws Exception {
        String filePath = "/your/home/test.png";

        URI uri = new URI("https://api.mch.weixin.qq.com/v3/merchant/media/upload");

        File file = new File(filePath);
        try (FileInputStream fileIs = new FileInputStream(file)) {
            String sha256 = DigestUtils.sha256Hex(fileIs);
            try (InputStream is = new FileInputStream(file)) {
                WechatPayUploadHttpPost request = new WechatPayUploadHttpPost.Builder(uri)
                        .withImage(file.getName(), sha256, is)
                        .build();

                try (CloseableHttpResponse response = httpClient.execute(request)) {
                    if (SC_OK != response.getStatusLine().getStatusCode()) {
                        log.error("res fail...");
                        return;
                    }
                    ;
                    HttpEntity entity = response.getEntity();
                    // do something useful with the response body
                    // and ensure it is fully consumed
                    String s = EntityUtils.toString(entity);
                    System.out.println(s);
                }
            }
        }
    }

    public void uploadFileTest() throws Exception {
        String filePath = "/your/home/test.png";

        URI uri = new URI("https://api.mch.weixin.qq.com/v3/merchant/media/upload");

        File file = new File(filePath);
        try (FileInputStream fileIs = new FileInputStream(file)) {
            String sha256 = DigestUtils.sha256Hex(fileIs);
            String meta = String.format("{\"filename\":\"%s\",\"sha256\":\"%s\"}", file.getName(), sha256);
            try (InputStream is = new FileInputStream(file)) {
                WechatPayUploadHttpPost request = new WechatPayUploadHttpPost.Builder(uri)
                        .withFile(file.getName(), meta, is)
                        .build();
                try (CloseableHttpResponse response = httpClient.execute(request)) {
                    if (SC_OK != response.getStatusLine().getStatusCode()) {
                        log.error("res fail...");
                        return;
                    }
                    HttpEntity entity = response.getEntity();
                    // do something useful with the response body
                    // and ensure it is fully consumed
                    String s = EntityUtils.toString(entity);
                    System.out.println(s);
                }
            }
        }
    }
}
