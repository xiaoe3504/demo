package com.psy.demo.service.impl;

import com.psy.demo.dto.AccessTokenDTO;
import com.psy.demo.global.BaseException;
import com.psy.demo.mapper.UserInfoMapper;
import com.psy.demo.service.AccessTokenService;
import com.psy.demo.service.HttpClientService;
import com.psy.demo.service.TaskService;
import com.psy.demo.utils.MyConstantString;
import com.psy.demo.vo.res.CozeAccessTokenRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Service
@Slf4j
@EnableScheduling
public class TaskServiceImpl implements TaskService {


    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    HttpClientService httpClientService;

    @Autowired
    AccessTokenService accessTokenService;

    //启动的时候设置下coze的token
    @PostConstruct
    public void init() {
        setAccessToken();
    }


    //每天查下如果过期就设置 is_member 为0
    @Scheduled(cron = "0 0 0 * * ?")
    public void dealIsMemberExpiresTime() {
        int res = userInfoMapper.dealIsMemberExpiresTime();
        log.info("cron task dealIsMemberTask start: " + LocalDateTime.now() + ",res:" + res);

    }

    //每天把非会员cnt设置为0
    @Scheduled(cron = "0 0 0 * * ?")
    public void initNotMemberMsg() {
        int res = userInfoMapper.initNotMemberMsgCnt();
        log.info("cron task dealIsNotMemberMsgTask start: " + LocalDateTime.now() + ",res:" + res);
    }

    //每天把非会员cnt设置为0
    @Scheduled(cron = "0 0 2 * * ?")
    public void setAccessTokenCoze() {
        setAccessToken();
    }

    private void setAccessToken() {
        CozeAccessTokenRes res = httpClientService.getAccessToken();
        AccessTokenDTO dto = genAccessTokenDto(res);
        accessTokenService.insertOrUpdate(dto);
    }

    private AccessTokenDTO genAccessTokenDto(CozeAccessTokenRes res) {
        if (res==null){
            throw new BaseException("coze  http get auth res null..");
        }
        AccessTokenDTO dto = new AccessTokenDTO();
        dto.setAccessToken(MyConstantString.TOKEN_SUFFIX + res.getAccess_token());
        dto.setExpiresIn(res.getExpires_in());
        dto.setType(MyConstantString.COZE.toLowerCase());
        dto.setTokenType(res.getToken_type());
        return dto;
    }


}
