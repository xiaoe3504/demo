package com.psy.demo.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@Slf4j
public class GetTokenUtils {

    // Authorization: <schema> <token>
// GET - getToken("GET", httpurl, "")
// POST - getToken("POST", httpurl, json)


    public static void main(String[] args) throws Exception {
//        testPost();
        testGet();
//        System.out.println(System.currentTimeMillis()/100);

    }


    private static void testGet() {
        HttpUrl httpurl = HttpUrl.parse("https://api.mch.weixin.qq.com/v3/certificates");
        String res = getToken(HttpGet.METHOD_NAME, httpurl, "");
        System.out.println("token:" + res);
    }


    private static void testPost() {
        HttpUrl httpurl = HttpUrl.parse(MyConstantString.JSAPI_URL);
        String openId = "o0Ya06wusUU-L8btHwi2BIAcj12U";
        String body = "{"
                + "\"amount\": {"
                + "\"total\": 1,"
                + "\"currency\": \"CNY\""
                + "},"
                + "\"mchid\": \"" + MyConstantString.MERCHANT_ID + "\","
                + "\"description\": \"Image形象店-深圳腾大-QQ公仔\","
                + "\"notify_url\": \"https://www.weixin.qq.com/wxpay/pay.php\","
                + "\"payer\": {"
                + "\"openid\": \"" + openId + "\"" + "},"
                + "\"out_trade_no\": \"2024072912345678901234567890\","
                + "\"goods_tag\": \"WXG\","
                + "\"appid\": \"" + MyConstantString.APPID + "\"" + "}";
        String res = getToken(HttpPost.METHOD_NAME, httpurl, body);
        System.out.println("token:" + res);
    }


    public static String getToken(String method, HttpUrl url, String body) {
//        String nonceStr = CommonUtils.generateUUIDString();
        String nonceStr = "50da70fb-7efa-42f7-bf9a-3a6f49adc0de";
//        long timestamp = System.currentTimeMillis() / 1000;
        long timestamp = 1722272152L;
        String message = buildMessage(method, url, timestamp, nonceStr, body);
        log.info("message:---------------");
        log.info(message);
        log.info("message:---------------");
        String signature;
        signature = sign(message.getBytes(StandardCharsets.UTF_8));
        log.info("signature:" + signature);
        System.out.println("signature:" + signature);
        String res = "mchid=\"" + MyConstantString.MERCHANT_ID + "\","
                + "nonce_str=\"" + nonceStr + "\","
                + "signature=\"" + signature + "\","
                + "timestamp=\"" + timestamp + "\","
                + "serial_no=\"" + MyConstantString.MERCHANT_SERIAL_NUMBER + "\"";
        res = MyConstantString.SCHEMA + " " + res;
        log.info("token:" + res);
        return res;
    }

    public static String sign(byte[] message) {
        String res = null;
        try {
            Signature sign = Signature.getInstance("SHA256withRSA");
            PrivateKey privateKey = PemUtil.loadPrivateKey(MyConstantString.PRIVATE_KEY);
            sign.initSign(privateKey);
            sign.update(message);
            res = Base64.getEncoder().encodeToString(sign.sign());
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            log.error("sign err:" + e.getMessage(), e);
        }
        return res;
    }

    public static String signRSA(String data, String priKey) throws Exception {
        //签名的类型
        Signature sign = Signature.getInstance("SHA256withRSA");
        //读取商户私钥,该方法传入商户私钥证书的内容即可
        byte[] keyBytes = Base64.getDecoder().decode(priKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        sign.initSign(privateKey);
        sign.update(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(sign.sign());
    }


    public static String buildMessage(String method, HttpUrl url, long timestamp, String nonceStr, String body) {
        String canonicalUrl = url.encodedPath();
        if (url.encodedQuery() != null) {
            canonicalUrl += "?" + url.encodedQuery();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(method).append("\n");
        sb.append(canonicalUrl).append("\n");
        sb.append(timestamp).append("\n");
        sb.append(nonceStr).append("\n");
        if (StringUtils.isNotEmpty(body)) {
            sb.append(body).append("\n");
        } else {
            sb.append("\n");
        }
        return sb.toString();
    }
}
