package com.psy.demo.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.psy.demo.dto.ConversationDTO;
import com.psy.demo.dto.MessageDTO;
import com.psy.demo.global.BaseException;
import com.psy.demo.mapper.*;
import com.psy.demo.service.ConversationService;
import com.psy.demo.utils.DateUtils;
import com.psy.demo.vo.req.ConversationReq;
import com.psy.demo.vo.req.MessageReq;
import com.psy.demo.vo.res.MessageVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class ConversationServiceImpl implements ConversationService {


    @Autowired
    ConversationMapper conversationMapper;

    @Autowired
    MessageMapper messageMapper;

    @Transactional
    @Override
    public int leaveMessage(ConversationReq req) {
        adjustReq(req);
        String clientId = req.getClientId();
        String psychologistId = req.getPsychologistId();
        String message = req.getMessage();
        Boolean isSenderPsychologist = req.getIsSenderPsychologist();

        ConversationDTO conversationDTO = ConversationDTO.builder()
                .psychologistId(psychologistId)
                .clientId(clientId)
                .build();
        int conversationId = getConversationId(conversationDTO);

        MessageDTO messageDTO = MessageDTO.builder()
                .conversationId(conversationId)
                .isSenderPsychologist(isSenderPsychologist)
                .message(message).build();
        int resMessage = messageMapper.insert(messageDTO);
        log.info("resMessage:" + resMessage);

        return resMessage;
    }

    @Override
    public List<MessageVO> getMessages(MessageReq req) {
        String psychologistId = req.getPsychologistId();
        String clientId = req.getClientId();
        if (StringUtils.isEmpty(psychologistId)) {
            throw new BaseException("getMessages error psychologistId null ");
        }
        if (StringUtils.isEmpty(clientId)) {
            throw new BaseException("getMessages error clientId null ");
        }
        List<MessageVO> messageVOS = messageMapper.selectMessagesByOpenId(req);
        messageVOS.forEach(e -> {
            e.setTime(DateUtils.getTimeInterval(e.getTime()));
        });
        return messageVOS;
    }

    @Override
    public List<MessageVO> getMessagesPsychologist(MessageReq req) {
        String psychologistId = req.getPsychologistId();
        if (StringUtils.isEmpty(psychologistId)) {
            throw new BaseException("getMessages error psychologistId null ");
        }
        //查的都是用户发来的
        List<MessageVO> messageVOS = messageMapper.selectMessagesByPsychologistId(req);
        messageVOS.forEach(e -> {
            e.setTime(DateUtils.getTimeInterval(e.getTime()));
        });
        return messageVOS;
    }

    @Override
    public int dealHasRead(MessageDTO dto) {
        if (dto.getId() == null || dto.getId() <= 0) {
            throw new BaseException("dealHasRead error dto id invalid ");
        }
        return messageMapper.dealHasRead(dto);
    }

    private int getConversationId(ConversationDTO conversationDTO) {
        ConversationDTO dtoExists = conversationMapper.selectById2(conversationDTO);
        int conversationId;
        if (dtoExists != null) {
            conversationId = dtoExists.getId();
            log.info("conversation exist:" + JSONObject.toJSONString(dtoExists));
        } else {
            int resConversation = conversationMapper.insert(conversationDTO);
            conversationId = conversationDTO.getId();
            log.info("addConversation res:" + resConversation);
        }
        return conversationId;
    }

    private void adjustReq(ConversationReq req) {
        if (StringUtils.isEmpty(req.getClientId())) {
            throw new BaseException("leaveMessage error clientId null ");
        }
        if (StringUtils.isEmpty(req.getPsychologistId())) {
            throw new BaseException("leaveMessage error psychologistId null ");
        }
        if (req.getClientId().equals(req.getPsychologistId())) {
            throw new BaseException("leaveMessage error clientId equals psychologistId ");
        }
        if (StringUtils.isEmpty(req.getMessage())) {
            throw new BaseException("leaveMessage error message null ");
        }
        if (null == req.getIsSenderPsychologist()) {
            throw new BaseException("leaveMessage error isSenderPsychologist null ");
        }
    }
}
