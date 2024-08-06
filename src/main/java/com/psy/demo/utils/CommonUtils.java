package com.psy.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.psy.demo.dto.UserInfoDTO;
import com.psy.demo.vo.res.FinalUserInfo;
import com.psy.demo.vo.res.RoleContent;
import com.psy.demo.vo.res.baichuan.Message;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.WebSocket;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.IntStream;

@Slf4j
public class CommonUtils {
    public static final int historyMaxCnt = 12000;
    public static final int baichuanMaxTokens = 512;

    // 讯飞鉴权方法
    public static String getAuthUrl(String hostUrl, String apiKey, String apiSecret) throws Exception {
        URL url = null;
        try {
            url = new URL(hostUrl);
        } catch (MalformedURLException e) {
            log.error("url error:"+e.getMessage(),e);
        }
        // 时间
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        String date = format.format(new Date());
        // 拼接
        String preStr = "host: " + url.getHost() + "\n" +
                "date: " + date + "\n" +
                "GET " + url.getPath() + " HTTP/1.1";
        // System.err.println(preStr);
        // SHA256加密
        Mac mac = Mac.getInstance("hmacsha256");
        SecretKeySpec spec = new SecretKeySpec(apiSecret.getBytes(StandardCharsets.UTF_8), "hmacsha256");
        mac.init(spec);

        byte[] hexDigits = mac.doFinal(preStr.getBytes(StandardCharsets.UTF_8));
        // Base64加密
        String sha = Base64.getEncoder().encodeToString(hexDigits);
        // System.err.println(sha);
        // 拼接
        String authorization = String.format("api_key=\"%s\", algorithm=\"%s\", headers=\"%s\", signature=\"%s\"", apiKey, "hmac-sha256", "host date request-line", sha);
        // 拼接地址
        HttpUrl httpUrl = Objects.requireNonNull(HttpUrl.parse("https://" + url.getHost() + url.getPath())).newBuilder().//
                addQueryParameter("authorization", Base64.getEncoder().encodeToString(authorization.getBytes(StandardCharsets.UTF_8))).//
                addQueryParameter("date", date).//
                addQueryParameter("host", url.getHost()).//
                build();

        // System.err.println(httpUrl.toString());
        return httpUrl.toString();
    }

    public static boolean canAddHistory(List<RoleContent> list) {  // 由于历史记录最大上线1.2W左右，需要判断是能能加入历史
        int history_length = 0;
        for (RoleContent temp : list) {
            history_length = history_length + temp.getContent().length();
        }
        if (history_length > historyMaxCnt) {
            list.remove(0);
            list.remove(1);
            list.remove(2);
            list.remove(3);
            list.remove(4);
            return false;
        } else {
            return true;
        }
    }

    public static boolean baiChuanCanAddHistory(List<Message> list) {
        int historyLength = 0;
        for (Message temp : list) {
            historyLength = historyLength + temp.getContent().length();
        }
        return historyLength <= baichuanMaxTokens;
    }

    public static UserInfoDTO genUserInfoDTO(FinalUserInfo finalUserInfo){
        UserInfoDTO dto=new UserInfoDTO();
        dto.setOpenId(finalUserInfo.getOpenId());
        dto.setAvatarUrl(finalUserInfo.getAvatarUrl());
        dto.setNickName(finalUserInfo.getNickName());
        return dto;
    }

    /**
     * 从数据库里的字段 9.8 原价19.8 来获取真实价格 double
     * @param isFreeStr
     * @param isFree
     * @return
     */
    public static double getPrice(String isFreeStr, boolean isFree) {
        double price = 0;
        if (!isFree && StringUtils.isNotEmpty(isFreeStr)) {
            String[] arr = isFreeStr.split(" ");
            if (StringUtils.isNotEmpty(arr[0])) {
                try {
                    price = Double.parseDouble(arr[0]);
                } catch (NumberFormatException e) {
                    log.error("price parse double err:" + e.getMessage(), e);
                }
            }
        }
        return price;
    }

    /**
     * 生成一个随机的16位字符串
     * @return 16位的随机字符串
     */
    public static String generateNonceStr() {
        // 初始化SecureRandom实例
        SecureRandom random = new SecureRandom();
        // 生成8字节的随机字节数组
        byte[] bytes = new byte[8];
        random.nextBytes(bytes);

        // 将字节数组转换为十六进制字符串
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            // 将每个字节转换为两个十六进制字符
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                // 如果是单个字符，前面补0
                sb.append('0');
            }
            sb.append(hex);
        }

        // 取前16个字符作为nonce_str
        // 注意：由于生成的是16个十六进制字符，所以实际上覆盖了生成的全部随机字节
        // 如果需要更短的nonce_str，可以在这里截取
        String res = sb.toString().substring(0, 16);
        return res.toUpperCase();

    }





}
