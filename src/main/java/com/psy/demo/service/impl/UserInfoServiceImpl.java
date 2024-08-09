package com.psy.demo.service.impl;

import com.psy.demo.global.BaseException;
import com.psy.demo.mapper.UserInfoMapper;
import com.psy.demo.service.UserInfoService;
import com.psy.demo.dto.UserInfoDTO;
import com.psy.demo.utils.StringUtil;
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
        if (StringUtils.isEmpty(userInfoDTO.getOpenId())) {
            throw new BaseException("openId不能为空");
        }
        if (StringUtils.isEmpty(userInfoDTO.getNickName())) {
            throw new BaseException("nickName不能为空");
        }
        if (StringUtils.isEmpty(userInfoDTO.getAvatarUrl())) {
            throw new BaseException("avatarUrl不能为空");
        }
        int res;
        try {
            res = userInfoMapper.insert(userInfoDTO);
        } catch (Exception e) {
            log.error("insert error:" + e.getMessage(), e);
            throw new BaseException("insert error: " + e.getMessage());
        }

        return res;
    }

    @Override
    public int saveOrUpdateNickname(UserInfoDTO userInfoDTO) {
        if (StringUtils.isEmpty(userInfoDTO.getNickName())){
            throw new BaseException("nickname can not be null");
        }
        if (StringUtils.isEmpty(userInfoDTO.getOpenId())){
            throw new BaseException("openId can not be null");
        }
       return userInfoMapper.insertOrUpdate(userInfoDTO);
    }

    @Override
    public UserInfoDTO getDTOByOpenId(UserInfoDTO userInfoDTO) {
        String openId=userInfoDTO.getOpenId();
        if (StringUtils.isEmpty(userInfoDTO.getOpenId())){
            throw new BaseException("openId can not be null");
        }
        return userInfoMapper.getDTOByOpenId(openId);
    }


}
