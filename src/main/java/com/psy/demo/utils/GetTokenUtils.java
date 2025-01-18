package com.psy.demo.utils;

import com.psy.demo.vo.req.SignReq;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.UUID;

@Slf4j
public class GetTokenUtils {

    public static void main(String[] args) throws Exception {
        String url = "https://api.mch.weixin.qq.com/v3/pay/transactions/out-trade-no/1733325307992_E35A9CF77EBDEE7B?mchid=1666640344";
        HttpUrl httpurl = HttpUrl.parse(url);
        String token = getToken(HttpGet.METHOD_NAME, httpurl, null);
        System.out.println(token);
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
        signature = sign(message.getBytes(StandardCharsets.UTF_8),MyConstantString.PRIVATE_KEY);
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

    public static String sign(byte[] message,String privateKeyString) {
        String res = null;
        try {
            Signature sign = Signature.getInstance("SHA256withRSA");
            PrivateKey privateKey = PemUtil.loadPrivateKey(privateKeyString);
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
                + (StringUtils.isEmpty(body) ? "" : body) + "\n";
    }

    public static String dealSign(SignReq signReq) {
        String appIdStr = signReq.getAppId();
        String timestamp = signReq.getTimestamp();
        String prepayIdStr = signReq.getPrepayIdStr();
        String nonceStr = signReq.getNonceStr();
        String message = appIdStr + "\n"
                + timestamp + "\n"
                + nonceStr + "\n"
                + prepayIdStr + "\n";
        return GetTokenUtils.sign(message.getBytes(StandardCharsets.UTF_8),MyConstantString.PRIVATE_KEY);
    }

}
