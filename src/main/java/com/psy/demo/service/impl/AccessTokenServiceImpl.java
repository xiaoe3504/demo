package com.psy.demo.service.impl;

import com.psy.demo.dto.AccessTokenDTO;
import com.psy.demo.mapper.AccessTokenMapper;
import com.psy.demo.service.AccessTokenService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.psy.demo.utils.MyConstantString.*;


@Service
@Slf4j
public class AccessTokenServiceImpl implements AccessTokenService {

    @Autowired
    AccessTokenMapper accessTokenMapper;

    @SneakyThrows
    @Override
    public String getCozeAccessToken() {
        return accessTokenMapper.selectByType(COZE.toLowerCase()).getAccessToken();
    }

    @Override
    public int insertOrUpdate(AccessTokenDTO dto) {
        return accessTokenMapper.insertOrUpdate(dto);
    }


}
