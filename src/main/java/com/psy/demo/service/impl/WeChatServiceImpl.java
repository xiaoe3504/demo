package com.psy.demo.service.impl;

import com.psy.demo.dto.UserInfoDTO;
import com.psy.demo.global.BaseException;
import com.psy.demo.mapper.UserInfoMapper;
import com.psy.demo.service.HttpClientService;
import com.psy.demo.service.UserInfoService;
import com.psy.demo.service.WeChatService;
import com.psy.demo.vo.res.WeChatCerRes;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.protocol.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class WeChatServiceImpl implements WeChatService {

    @Autowired
    HttpClientService httpClientService;


    @Override
    public WeChatCerRes dealCer() {
        return httpClientService.dealCer();
    }
}
