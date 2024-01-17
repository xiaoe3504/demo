package com.psy.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.psy.demo.vo.res.RoleContent;
import lombok.extern.slf4j.Slf4j;
import okhttp3.WebSocket;

import java.util.List;
import java.util.UUID;

// 线程来发送音频与参数
@Slf4j
public class SendThread extends Thread {

    private WebSocket webSocket;
    private String appid;
    private List<RoleContent> historyList;
    private String NewQuestion;
    private boolean wsCloseFlag;


    public SendThread(WebSocket webSocket, String appid, List<RoleContent> historyList,
                      String NewQuestion, boolean wsCloseFlag) {
        this.webSocket = webSocket;
        this.appid = appid;
        this.historyList = historyList;
        this.NewQuestion = NewQuestion;
        this.wsCloseFlag = wsCloseFlag;
    }

    public void run() {
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
            log.error(e.getMessage() + e);
            e.printStackTrace();
        }
    }
}
