package com.psy.demo.controller;

import com.psy.demo.service.impl.XunFeiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@ServerEndpoint("/ws")
@Component
@Slf4j
public class MyWebSocketServer {

    private static XunFeiService xunFeiService;
    /**
     * 注意:@ServerEndpoint 不能直接用单例的@autowired
     **/
    private ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    @Autowired
    private void setXunFeiService(XunFeiService xunFeiService) {
        MyWebSocketServer.xunFeiService = xunFeiService;
        xunFeiService.setLockAndCondition(lock, condition);
    }

    /**
     * 连接成功
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        log.info("连接成功");
    }

    /**
     * 连接关闭
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        log.info("连接关闭");
    }


    /**
     * 接收到消息
     *
     * @param text
     */
    @OnMessage
    public String onMsg(String text) {
        log.info("接收到客户问题：" + text);
        //接收问题去调用讯飞官方
        xunFeiService.newWebSocket(text);
        log.info("threadId main:"+Thread.currentThread().getId()+"");
        try {
            lock.lock();
            condition.await();
        } catch (InterruptedException e) {
            log.error("lock await error:" + e.getMessage() + e);
        }finally {
            lock.unlock();
        }
        String totalAnswer = xunFeiService.getTotalAnswer();
        return totalAnswer;
    }
}