package com.psy.demo.controller;

import com.psy.demo.service.BaiChuanService;
import com.psy.demo.service.impl.XunFeiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint("/ws1")
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
    private static  BaiChuanService baiChuanService;
    /**
     * 注意:@ServerEndpoint是多例 不能直接用单例的@autowired
     **/
    @Autowired
    private void setBaiChuanService(BaiChuanService baiChuanService) {
        MyWebSocketServer1.baiChuanService = baiChuanService;
    }
    /**
     * 连接成功
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
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
        //接收问题去调用百川
        String res = baiChuanService.dealMsg(msg);
        log.info("baichuan answer msg：" + msg);
        return res;
    }
}