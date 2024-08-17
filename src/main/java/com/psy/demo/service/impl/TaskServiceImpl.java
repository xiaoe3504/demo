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

    @Scheduled(cron = "0 48 20 * * ?")
    public void dealIsMemberTask() {
        int res = userInfoMapper.updateIsMemberExpiresTime();
        log.info("cron task start: " + LocalDateTime.now() + ",res:" + res);
    }

}
