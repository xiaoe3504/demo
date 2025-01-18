package com.psy.demo.service.impl;

import com.psy.demo.dto.AccessTokenDTO;
import com.psy.demo.dto.CozeConversationDTO;
import com.psy.demo.dto.CozeMessageDTO;
import com.psy.demo.mapper.AccessTokenMapper;
import com.psy.demo.mapper.CozeConversationMapper;
import com.psy.demo.mapper.CozeMessageMapper;
import com.psy.demo.service.CozeService;
import com.psy.demo.service.HttpClientService;
import com.psy.demo.vo.res.CozeBootRes;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.psy.demo.utils.MyConstantString.COZE;


@Service
@Slf4j
public class CozeServiceImpl implements CozeService {

    @Autowired
    AccessTokenMapper accessTokenMapper;

    @Autowired
    HttpClientService httpClientService;

    @Autowired
    CozeConversationMapper cozeConversationMapper;

    @Autowired
    CozeMessageMapper cozeMessageMapper;


    @SneakyThrows
    @Override
    public String getCozeAccessToken() {
        return accessTokenMapper.selectByType(COZE.toLowerCase()).getAccessToken();
    }

    @Override
    public int insertOrUpdateAccessToken(AccessTokenDTO dto) {
        return accessTokenMapper.insertOrUpdate(dto);
    }

    @Override
    public String createConversation() {
        String cozeAccessToken = getCozeAccessToken();
        return httpClientService.createConversation(cozeAccessToken);
    }

    @Override
    public String chat(String conversationId, String openId, String content, String bootId) {
        String cozeAccessToken = getCozeAccessToken();
        CozeBootRes cozeBootRes= httpClientService.chat(conversationId, openId, content, cozeAccessToken, bootId);

        String chatId = cozeBootRes.getChat_id();
        String contentFromBoot = cozeBootRes.getContent();
        saveChat(conversationId, openId, content, bootId, contentFromBoot,chatId);
        return contentFromBoot;
    }

    @Async("taskExecutor")
    public void saveChat(String conversationId, String openId, String content,
                          String bootId, String contentFromBoot,String chatId) {
        CozeMessageDTO cozeMessageDTOUser = getCozeMessageDTOUser(conversationId, content,chatId);
        CozeMessageDTO cozeMessageDTOBoot = getCozeMessageDTOBoot(conversationId, contentFromBoot,chatId);
        int resUser = cozeMessageMapper.insert(cozeMessageDTOUser);
        int resBoot = cozeMessageMapper.insert(cozeMessageDTOBoot);
        log.info("coze message insert resUser:" + resUser + ",resBoot:" + resBoot);

        CozeConversationDTO cozeConversationDTO = genCozeConversationDTO(conversationId, openId, bootId);
        int resConversation = cozeConversationMapper.insertOrUpdate(cozeConversationDTO);
        log.info("coze conversation insert resConversation:" + resConversation);

    }

    @NotNull
    private CozeConversationDTO genCozeConversationDTO(String conversationId, String openId, String bootId) {
        CozeConversationDTO cozeConversationDTO = new CozeConversationDTO();
        cozeConversationDTO.setCozeConversationId(conversationId);
        cozeConversationDTO.setOpenId(openId);
        cozeConversationDTO.setBootId(bootId);
        return cozeConversationDTO;
    }

    @NotNull
    private CozeMessageDTO getCozeMessageDTOBoot(String conversationId, String content,String chatId) {
        CozeMessageDTO cozeMessageDTOUser = new CozeMessageDTO();
        cozeMessageDTOUser.setContent(content);
        cozeMessageDTOUser.setIsSenderBoot(1);
        cozeMessageDTOUser.setCozeConversationId(conversationId);
        cozeMessageDTOUser.setCozeMessageId(chatId);
        return cozeMessageDTOUser;
    }

    @NotNull
    private CozeMessageDTO getCozeMessageDTOUser(String conversationId, String content,String chatId) {
        CozeMessageDTO cozeMessageDTOUser = new CozeMessageDTO();
        cozeMessageDTOUser.setContent(content);
        cozeMessageDTOUser.setIsSenderBoot(0);
        cozeMessageDTOUser.setCozeConversationId(conversationId);
        cozeMessageDTOUser.setCozeMessageId(chatId);
        return cozeMessageDTOUser;
    }


    @Override
    public List<String> getLastContents(String conversationId) {

        return null;
    }


}
