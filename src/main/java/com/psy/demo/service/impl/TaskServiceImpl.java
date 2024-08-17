package com.psy.demo.service.impl;

import com.psy.demo.mapper.UserInfoMapper;
import com.psy.demo.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@EnableScheduling
public class TaskServiceImpl implements TaskService {

    @Autowired
    UserInfoMapper userInfoMapper;
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


}
