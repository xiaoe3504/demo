package com.psy.demo.utils;

import com.psy.demo.controller.LoginController;
import com.psy.demo.vo.res.UserInfo;
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
            e.printStackTrace();
            return null;
        }
    }

    public static void mockUserInfoRes(UserInfoRes res) {
        String encryptedData = "7LoFF6oImPRRD/bUCZO6jdIADdYRP7KkflF9EQTticYtxfky5RGFm5BvwxmAI61grAfU0nF0qClEifrLoeuvSWpI7R2sJIwSSFr7lSe864l0b86S5j1bxLQDWBo/sxcRWa0cNeXu7UwbnGAtuY4s/l7yikf3J3LL52eW0tdbCyf/d//66+LHoDgbCFIf53xxIYkqCsjaNHd/Hdu4NBGp712fACpR28mwei4b5b1l2VfRcsL/hIRL/QhcugtyOoGY7hVF5a40zn+2GWaooVkRdOIpzKxG37RZ5TotPAPtUjm5z/0IXwj+oYKF4xtg/Ctvz4FXHcfqhSppuWIx2P5DLBwOUeOi9pM2fANG633CHHWlwDsWfaFZJV+mC5wEGUNOrnP+4Xg2Mq638Njnl1P6/9wSuZoagyWiWF+foCWOkv+EW8sn5cylqY+nbV1tvhCh+tAppih38xt0AYv4nIaV8Q==";
        res.setEncryptedData(encryptedData);
        String iv = "mF0iSde5NcylirMqugYIJw==";
        res.setIv(iv);
        String sessionKey = "MbPFdfreHEMk/hOU6Fa6eg==";
        res.setSessionKey(sessionKey);
        UserInfo userInfo = mockUserInfo();
        res.setUserInfo(userInfo);
    }

    @NotNull
    private static  UserInfo mockUserInfo() {
        UserInfo userInfo = new UserInfo();
        String avatarUrl = "https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132";
        userInfo.setAvatarUrl(avatarUrl);
        String nickName = "微信用户";
        userInfo.setNickName(nickName);
        return userInfo;
    }

    public static void main(String[] args) {
        // 示例参数
        UserInfoRes res=new UserInfoRes();
        mockUserInfoRes(res);

        // 解密
        String userInfoJson = decode(res.getSessionKey(), res.getEncryptedData(), res.getIv());

        // 打印解密后的用户信息（JSON 格式）
        System.out.println(userInfoJson);

        // 接下来可以解析JSON字符串获取具体信息
    }
}
