package com.psy.demo.controller;

import com.psy.demo.service.BaiChuanService;
import com.psy.demo.service.UserInfoService;
import com.psy.demo.utils.MyConstantString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import static com.psy.demo.utils.MyConstantString.DEFAULT_ANSWER;

@ServerEndpoint("/ws1/{openId}")
@Component
@Slf4j
public class MyWebSocketServer1 {
    private static final AtomicInteger counter = new AtomicInteger(0);
    // 客户端编号
    private Integer number;
    // 客户端websocket连接集合
    private static final Set<MyWebSocketServer1> connections = new CopyOnWriteArraySet<>();
    // WebSocket会话对象
    private Session session = null;
    private String openId = null;
    /**
     * 注意:@ServerEndpoint是多例 不能直接用单例的@autowired
     **/
    private static BaiChuanService baiChuanService;

    private static UserInfoService userInfoService;

    public MyWebSocketServer1() {
        number = counter.incrementAndGet();
    }

    public static Set<MyWebSocketServer1> getConnections() {
        return connections;
    }

    public Session getSession() {
        return session;
    }


    /**
     * 注意:@ServerEndpoint是多例 不能直接用单例的@autowired
     **/
    @Autowired
    private void setBaiChuanService(BaiChuanService baiChuanService) {
        MyWebSocketServer1.baiChuanService = baiChuanService;
    }

    @Autowired
    private void setUserInfoService(UserInfoService userInfoService) {
        MyWebSocketServer1.userInfoService = userInfoService;
    }

    /**
     * 连接成功
     *
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("openId") String openId, Session session) {
        this.openId = openId;
        log.info("chat openId:" + openId);
        this.session = session;
        connections.add(this);
        log.info("连接成功");
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose() {
        log.info("连接关闭");
        try {
            this.session.close();
        } catch (IOException e) {
            log.error("onClose err:" + e.getMessage(), e);
        } finally {
            connections.remove(this);
        }
    }

    @OnError
    public void error(Throwable t) {
        log.error("client: error" + number + t.getMessage(), t);
    }


    /**
     * 接收到消息
     *
     * @param msg
     */
    @OnMessage
    public String onMsg(String msg) {
        log.info("ws1 receive msg：" + msg);
        boolean isMember = userInfoService.adjustIsMember(openId);
        int msgCnt = userInfoService.dealAddNotMemberMsgCnt(openId);
        if (!isMember && msgCnt >= 10) {
            return MyConstantString.DAY_MSG_LIMIT;
        }
        String res;
        try {
            //接收问题去调用百川
            res = baiChuanService.dealMsg(msg);
        } catch (Exception e) {
            log.error("onMessage error:" + e.getMessage(), e);
            res = DEFAULT_ANSWER;
        }
        log.info("baichuan answer msg：" + msg);
        return res;
    }
}