package com.psy.demo.service.impl;

import com.google.gson.Gson;
import com.psy.demo.utils.CommonUtils;
import com.psy.demo.utils.SendThread;
import com.psy.demo.vo.res.Header;
import com.psy.demo.vo.res.JsonParse;
import com.psy.demo.vo.res.RoleContent;
import com.psy.demo.vo.res.Text;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Service
public class XunFeiService extends WebSocketListener {
    // 地址与鉴权信息  https://spark-api.xf-yun.com/v1.1/chat   1.5地址  domain参数为general
    // 地址与鉴权信息  https://spark-api.xf-yun.com/v2.1/chat   2.0地址  domain参数为generalv2
//    public static final String hostUrl = "https://spark-api.xf-yun.com/v2.1/chat";
    @Value("${xunfei.hostUrl}")
    public String hostUrl;
    @Value("${xunfei.appid}")
    public String appid;
    @Value("${xunfei.apiSecret}")
    public String apiSecret;
    @Value("${xunfei.apiKey}")
    public String apiKey;


    public static List<RoleContent> historyList = new ArrayList<>(); // 对话历史存储集合

    public static String totalAnswer = ""; // 大模型的答案汇总

    // 环境治理的重要性  环保  人口老龄化  我爱我的祖国
    public static String NewQuestion = "";

    public static final Gson gson = new Gson();

    // 个性化参数
    private String userId;
    private boolean wsCloseFlag;

    public void setWsCloseFlag(boolean wsCloseFlag) {
        this.wsCloseFlag = wsCloseFlag;
    }

    public boolean getWsCloseFlag() {
        return wsCloseFlag;
    }

    public String getTotalAnswer() {
        return totalAnswer;
    }


    //send函数
    public void createWebSocket(String text) {
        // 个性化参数入口，如果是并发使用，可以在这里模拟
        NewQuestion = text;
        // 构建鉴权url
        String authUrl = null;
        try {
            authUrl = CommonUtils.getAuthUrl(hostUrl, apiKey, apiSecret);
        } catch (Exception e) {
            log.error("xunfei auth error:" + e.getMessage(), e);
        }
        OkHttpClient client = new OkHttpClient.Builder().build();
        String url = Objects.requireNonNull(authUrl)
                .replace("http://", "ws://").replace("https://", "wss://");
        Request request = new Request.Builder().url(url).build();
        totalAnswer = "";
        WebSocket webSocket = client.newWebSocket(request, this);
        log.info("new webSocket:" + webSocket.toString());
    }


    @Override
    public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
        super.onOpen(webSocket, response);
        log.info("大模型开启:" + webSocket.toString());

        SendThread sendThread = new SendThread(webSocket, appid, historyList, NewQuestion, wsCloseFlag);
        sendThread.start();
    }

    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
        log.info("userId:" + userId + ",答案：" + text);
        JsonParse myJsonParse = gson.fromJson(text, JsonParse.class);
        Header header = myJsonParse.getHeader();
        int code = header.getCode();
        if (code != 0) {
            log.info("发生错误，错误码为：" + code);
            String sid = header.getSid();
            log.info("本次请求的sid为：" + sid);
            webSocket.close(1000, "");
        }
        List<Text> textList = myJsonParse.getPayload().getChoices().getText();
        for (Text temp : textList) {
            String content = temp.getContent();
            log.info("content:" + content);
            totalAnswer = totalAnswer + content;
        }
        if (header.getStatus() == 2) {
            // 可以关闭连接，释放资源
            log.info("");
            log.info("*************************************************************************************");
            if (CommonUtils.canAddHistory(historyList)) {
                RoleContent roleContent = new RoleContent();
                roleContent.setRole("assistant");
                roleContent.setContent(totalAnswer);
                historyList.add(roleContent);
            } else {
                historyList.remove(0);
                RoleContent roleContent = new RoleContent();
                roleContent.setRole("assistant");
                roleContent.setContent(totalAnswer);
                historyList.add(roleContent);
            }
            wsCloseFlag = true;
        }

    }

    @Override
    public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, Response response) {
        super.onFailure(webSocket, t, response);
        try {
            if (null != response) {
                int code = response.code();
                log.info("onFailure code:" + code);
                log.info("onFailure body:" + response.body().string());
                if (101 != code) {
                    log.info("connection failed");
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.error("xunfei onFailure error:" + e.getMessage(), e);
        }
    }


}