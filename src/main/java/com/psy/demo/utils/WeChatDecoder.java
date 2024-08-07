package com.psy.demo.utils;

import com.psy.demo.dto.UserInfoDTO;
import com.psy.demo.global.BaseException;
import com.psy.demo.vo.res.UserInfoVO;
import com.psy.demo.vo.res.UserInfoRes;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.jetbrains.annotations.NotNull;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Security;

import static com.psy.demo.utils.MockUtil.mockUserInfoRes;

@Slf4j
public class WeChatDecoder {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }


    public static String decode(String sessionKey, String encryptedData, String iv) {
        try {
            // 转换sessionKey为16进制字节数组
            byte[] sessionKeyBytes = Base64.decode(sessionKey);
            SecretKeySpec keySpec = new SecretKeySpec(sessionKeyBytes, "AES");

            // 转换iv为字节数组
            byte[] ivBytes = Base64.decode(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

            // 初始化Cipher对象
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

            // 转换encryptedData为字节数组
            byte[] encryptedDataBytes = Base64.decode(encryptedData);

            // 解密
            byte[] resultBytes = cipher.doFinal(encryptedDataBytes);

            // 转换结果为字符串并返回
            return new String(resultBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new BaseException("wechatDecoder decode err");
        }
    }

}
