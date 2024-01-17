package com.psy.demo.controller;

import com.psy.demo.service.impl.XunFeiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint("/ws")
@Component
@Slf4j
public class MyWebSocketServer {

    private static final AtomicInteger counter = new AtomicInteger(0);
    // 客户端编号
    private Integer number;
    // 客户端websocket连接集合
    private static final Set<MyWebSocketServer> connections = new CopyOnWriteArraySet<>();
    // WebSocket会话对象
    private Session session = null;

    public MyWebSocketServer() {
        number = counter.incrementAndGet();
    }

    public static Set<MyWebSocketServer> getConnections() {
        return connections;
    }

    public Session getSession() {
        return session;
    }

    private static XunFeiService xunFeiService;

    /**
     * 注意:@ServerEndpoint是多例 不能直接用单例的@autowired
     **/


    @Autowired
    private void setXunFeiService(XunFeiService xunFeiService) {
        MyWebSocketServer.xunFeiService = xunFeiService;
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
            log.error("onClose err:" + e.getMessage() , e);
        } finally {
            connections.remove(this);
        }
    }

    @OnError
    public void error(Throwable t) {
        log.error("client: error" + number + t.getMessage() , t);
    }


    /**
     * 接收到消息
     *
     * @param msg
     */
    @OnMessage
    public String onMsg(String msg) {
        log.info("接收到客户问题：" + msg);
        //接收问题去调用讯飞官方
        xunFeiService.createWebSocket(msg);
        log.info("threadId main await:" + Thread.currentThread().getId() + "");
        //需xunFeiService通知可以拿answer
        String totalAnswer = "";

        while (!xunFeiService.getWsCloseFlag()) {
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                log.error("thread sleep error:" + e.getMessage() , e);
            }
            totalAnswer = xunFeiService.getTotalAnswer();
        }
        log.info("totalAnswer:" + totalAnswer);
        return totalAnswer;
    }
}