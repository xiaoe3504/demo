package com.psy.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.psy.demo.dto.UserInfoDTO;
import com.psy.demo.vo.res.FinalUserInfo;
import com.psy.demo.vo.res.RoleContent;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.WebSocket;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class CommonUtils {
    public static final int historyMaxCnt = 12000;

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

    public static void sendMsg(WebSocket webSocket, String appid, List<RoleContent> historyList,
                               String NewQuestion, boolean wsCloseFlag) {
        try {
            JSONObject requestJson = new JSONObject();

            JSONObject header = new JSONObject();  // header参数
            header.put("app_id", appid);
            header.put("uid", UUID.randomUUID().toString().substring(0, 10));

            JSONObject parameter = new JSONObject(); // parameter参数
            JSONObject chat = new JSONObject();
            chat.put("domain", "generalv2");
            chat.put("temperature", 0.5);
            chat.put("max_tokens", 4096);
            parameter.put("chat", chat);

            JSONObject payload = new JSONObject(); // payload参数
            JSONObject message = new JSONObject();
            JSONArray text = new JSONArray();

            // 历史问题获取
            if (historyList.size() > 0) {
                for (RoleContent tempRoleContent : historyList) {
                    text.add(JSON.toJSON(tempRoleContent));
                }
            }

            // 最新问题
            RoleContent roleContent = new RoleContent();
            roleContent.setRole("user");
            roleContent.setContent(NewQuestion);
            text.add(JSON.toJSON(roleContent));
            historyList.add(roleContent);

            message.put("text", text);
            payload.put("message", message);

            requestJson.put("header", header);
            requestJson.put("parameter", parameter);
            requestJson.put("payload", payload);
            // System.err.println(requestJson); // 可以打印看每次的传参明细
            webSocket.send(requestJson.toString());
            // 等待服务端返回完毕后关闭
            while (true) {
                // System.err.println(wsCloseFlag + "---");
                Thread.sleep(200);
                if (wsCloseFlag) {
                    break;
                }
            }
            webSocket.close(1000, "");
        } catch (Exception e) {
            log.error(e.getMessage() , e);
            e.printStackTrace();
        }
    }

    public static UserInfoDTO genUserInfoDTO(FinalUserInfo finalUserInfo){
        UserInfoDTO dto=new UserInfoDTO();
        dto.setOpenId(finalUserInfo.getOpenId());
        dto.setAvatarUrl(finalUserInfo.getAvatarUrl());
        dto.setNickName(finalUserInfo.getNickName());
        return dto;
    }
}
