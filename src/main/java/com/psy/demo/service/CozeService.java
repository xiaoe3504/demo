package com.psy.demo.service;


import com.psy.demo.dto.AccessTokenDTO;
import lombok.SneakyThrows;

import java.util.List;


public interface CozeService {

    @SneakyThrows
    String getCozeAccessToken();

    int insertOrUpdateAccessToken(AccessTokenDTO dto);

    String createConversation();

    String chat(String conversationId, String openId, String content,String bootId);

    List<String> getLastContents(String conversationId);
}
