package com.psy.demo.service.impl;


import com.psy.demo.global.BaseException;
import com.psy.demo.service.CozeService;
import com.psy.demo.utils.MyConstantString;
import com.psy.demo.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Objects;

@Component
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {

    @Autowired
    CozeService cozeService;


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String payload = message.getPayload();
        String path = Objects.requireNonNull(session.getUri()).getPath();
        String[] pathSegments = path.split("/");
        String openId = pathSegments[2]; // Assuming the param1 is in the specified position
        String bootId = pathSegments[3]; // Assuming the param2 is in the specified position

        String conversationId = (String) session.getAttributes().get("conversationId");
        if (StringUtils.isEmpty(conversationId)) {
            conversationId = cozeService.createConversation();
            session.getAttributes().put("conversationId", conversationId);
        }

        String resCoze = cozeService.chat(conversationId, openId, payload, bootId);
        session.sendMessage(new TextMessage(resCoze));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Extract userId from the session URI
        String path = Objects.requireNonNull(session.getUri()).getPath();
        String[] pathSegments = path.split("/");
        String openId = pathSegments[2]; // Assuming the param1 is in the specified position
        String bootId = pathSegments[3]; // Assuming the param2 is in the specified position
        if (!MyConstantString.COZE_BOOT_ID_MALE.equals(bootId)
                && !MyConstantString.COZE_BOOT_ID_FEMALE.equals(bootId)) {
            throw new BaseException("coze boot id error...");
        }
        log.info("Connection established with openId: " + openId + ",bootId:" + bootId);

    }

}
