package com.psy.demo.utils;

import com.google.gson.Gson;
import com.psy.demo.vo.res.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.*;

@Slf4j
public class XunFeiWebSocketDemo extends WebSocketListener {
    // 地址与鉴权信息  https://spark-api.xf-yun.com/v1.1/chat   1.5地址  domain参数为general
    // 地址与鉴权信息  https://spark-api.xf-yun.com/v2.1/chat   2.0地址  domain参数为generalv2
//    public static final String hostUrl = "https://spark-api.xf-yun.com/v2.1/chat";
    @Value("xunfei.hostUrl")
    public static final String hostUrl = "https://spark-api.xf-yun.com/v3.1/chat";
    @Value("xunfei.appid")
    public static final String appid = "775bbbf8";
    @Value("xunfei.apiSecret")
    public static final String apiSecret = "YWMxYTg1Y2I0Nzg0YTMwZTk3MzEwMzJk";
    @Value("xunfei.apiKey")
    public static final String apiKey = "3e419c89339513bd620b6814c8bed242";

    public static final int historyMaxCnt = 12000;

    public static List<RoleContent> historyList = new ArrayList<>(); // 对话历史存储集合

    public static String totalAnswer = ""; // 大模型的答案汇总

    // 环境治理的重要性  环保  人口老龄化  我爱我的祖国
    public static String NewQuestion = "";

    public static final Gson gson = new Gson();

    // 个性化参数
    private String userId;
    private Boolean wsCloseFlag;

    private static Boolean totalFlag = true; // 控制提示用户是否输入

    // 构造函数
    public XunFeiWebSocketDemo(String userId, Boolean wsCloseFlag) {
        this.userId = userId;
        this.wsCloseFlag = wsCloseFlag;
    }

    // 主函数
    public static void main(String[] args) throws Exception {
        // 个性化参数入口，如果是并发使用，可以在这里模拟
        while (true) {
            if (totalFlag) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("我：");
                totalFlag = false;
                NewQuestion = scanner.nextLine();
                // 构建鉴权url
                String authUrl = CommonUtils.getAuthUrl(hostUrl, apiKey, apiSecret);
                OkHttpClient client = new OkHttpClient.Builder().build();
                String url = authUrl.replace("http://", "ws://").replace("https://", "wss://");
                Request request = new Request.Builder().url(url).build();
                for (int i = 0; i < 1; i++) {
                    totalAnswer = "";
                    WebSocket webSocket = client.newWebSocket(request, new XunFeiWebSocketDemo(i + "",
                            false));
                    log.info(webSocket.toString());
                }
            } else {
                Thread.sleep(200);
            }
        }
    }

    @Override
    public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        super.onClosed(webSocket, code, reason);
    }

    @Override
    public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        super.onClosing(webSocket, code, reason);
    }

    @Override
    public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
        super.onOpen(webSocket, response);
        System.out.print("大模型：");
        SendThread SendThread = new SendThread(webSocket, appid, historyList, NewQuestion, wsCloseFlag);
        SendThread.start();
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        // log.info(userId + "用来区分那个用户的结果" + text);
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
            totalFlag = true;
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
            e.printStackTrace();
        }
    }


}