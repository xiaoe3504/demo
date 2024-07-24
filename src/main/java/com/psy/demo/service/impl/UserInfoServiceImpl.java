package com.psy.demo.service.impl;

import com.psy.demo.global.BaseException;
import com.psy.demo.mapper.UserInfoMapper;
import com.psy.demo.service.UserInfoService;
import com.psy.demo.dto.UserInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public int saveUser(UserInfoDTO userInfoDTO) {
        if (StringUtils.isEmpty(userInfoDTO.getOpenId())){
            throw new BaseException("openId不能为空");
        }
        if (StringUtils.isEmpty(userInfoDTO.getNickName())){
            throw new BaseException("nickName不能为空");
        }
        if (StringUtils.isEmpty(userInfoDTO.getAvatarUrl())){
            throw new BaseException("avatarUrl不能为空");
        }
        return userInfoMapper.insert(userInfoDTO);
    }
}
