package com.psy.demo.utils.coze;


import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.psy.demo.global.BaseException;
import com.psy.demo.utils.GetTokenUtils;
import com.psy.demo.utils.MyConstantString;
import com.psy.demo.utils.PemUtil;
import com.psy.demo.utils.RandomUtils;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Slf4j
public class JWTUtils {

    public static String getJWT() {
        PrivateKey privateKey = PemUtil.loadPrivateKey(MyConstantString.PRIVATE_KEY_COZE);
        // 创建签名器
        JWSSigner signer = new RSASSASigner(privateKey);

        // 建立JWT声明，包含iss、iat、exp、aud和jti
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .issuer(MyConstantString.APPID_COZE)  // todo: app_id
                .issueTime(new Date())
                .expirationTime(new Date(new Date().getTime() + MyConstantString.EXPIRE_TIME_COZE)) // 设置过期时间为1小时后
                .audience(MyConstantString.AUD_COZE)  // todo: token接口的域名
                .jwtID(UUID.randomUUID().toString())
                .build();

        // 创建JWT头部，包含kid
        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256)
                .keyID(MyConstantString.PUBLIC_KEY_COZE) // todo: 公钥指纹
                .build();

        // 使用头部和声明生成签名的JWT
        SignedJWT signedJWT = new SignedJWT(header, claimsSet);
        try {
            signedJWT.sign(signer);
        } catch (JOSEException e) {
            throw new BaseException("gen jwt error");
        }

        // 生成JWT
        String jwt = signedJWT.serialize();

        log.info("Generated JWT: " + jwt);

        return jwt;
    }


    public static void main(String[] args) {
//        long start = System.currentTimeMillis() / 1000;
//        long end = start + 3600;
        getJWT();

    }
}
