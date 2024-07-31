package com.psy.demo.utils;

import com.psy.demo.vo.req.SignReq;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.UUID;

@Slf4j
public class GetTokenUtils {

    // Authorization: <schema> <token>
// GET - getToken("GET", httpurl, "")
// POST - getToken("POST", httpurl, json)


    public static void main(String[] args) throws Exception {
//        testPost();
//        System.out.println(System.currentTimeMillis()/100);
    }


    private static void testGet() {
        HttpUrl httpurl = HttpUrl.parse(MyConstantString.JSAPI_URL);
        String res = getToken(HttpPost.METHOD_NAME, httpurl, "{\n" +
                "    \"amount\": {\n" +
                "        \"total\": 1,\n" +
                "        \"currency\": \"CNY\"\n" +
                "    },\n" +
                "    \"mchid\": \"1666640344\",\n" +
                "    \"attach\": \"attach\",\n" +
                "    \"description\": \"心芽知道\",\n" +
                "    \"notify_url\": \"https://www.weixin.qq.com/wxpay/pay.php\",\n" +
                "    \"payer\": {\n" +
                "        \"openid\": \"o0Ya06wusUU-L8btHwi2BIAcj12U\"\n" +
                "    },\n" +
                "    \"out_trade_no\": \"1722408138159641E3892F00EFADD\",\n" +
                "    \"goods_tag\": \"WXG\",\n" +
                "    \"appid\": \"wxb1cd5bc64dbc5980\"\n" +
                "}");
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
        String nonceStr = UUID.randomUUID().toString();
//        String nonceStr = "465b1797-74a9-450a-a4e6-2372fb57c37a";
        long timestamp = System.currentTimeMillis() / 1000;
//        long timestamp = 1722409630L;
        String message = buildMessage(method, url, timestamp, nonceStr, body);
        log.info("message:---------------");
        log.info(message);
        log.info("message:---------------");
        String signature;
        signature = sign(message.getBytes(StandardCharsets.UTF_8));
        log.info("signature:" + signature);
        System.out.println("signature:" + signature);
        String res = "mchid=\"" + MyConstantString.MERCHANT_ID + "\","
                + "serial_no=\"" + MyConstantString.MERCHANT_SERIAL_NUMBER + "\","
                + "timestamp=\"" + timestamp + "\","
                + "nonce_str=\"" + nonceStr + "\","
                + "signature=\"" + signature + "\"";
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
        return method + "\n"
                + canonicalUrl + "\n"
                + timestamp + "\n"
                + nonceStr + "\n"
                + body + "\n";
    }

    public static String dealSign(SignReq signReq) {
        String appIdStr = signReq.getAppId();
        String timestamp = signReq.getTimestamp();
        String nonceStr = signReq.getNonceStr();
        String prepayIdStr = signReq.getPrepayIdStr();
        String message = appIdStr + "\n"
                + timestamp + "\n"
                + nonceStr + "\n"
                + prepayIdStr + "\n";
        return GetTokenUtils.sign(message.getBytes(StandardCharsets.UTF_8));
    }

}
