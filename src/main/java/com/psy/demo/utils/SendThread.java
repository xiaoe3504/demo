package com.psy.demo.utils;

import com.psy.demo.vo.res.RoleContent;
import lombok.extern.slf4j.Slf4j;
import okhttp3.WebSocket;

import java.util.List;

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
        CommonUtils.sendMsg(webSocket, appid, historyList,
                NewQuestion, wsCloseFlag);
    }


}
