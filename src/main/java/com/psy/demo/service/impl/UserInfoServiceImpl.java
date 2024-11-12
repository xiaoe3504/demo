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
    public int dealAdd(String openId) {
        UserInfoDTO userInfoDTO = UserInfoDTO.builder().openId(openId).build();
        if (adjustUserExist(openId)) {
            return -1;
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

    private boolean adjustUserExist(String openId) {
        UserInfoDTO dtoDB = userInfoMapper.getDTOByOpenId(openId);
        if (dtoDB != null) {
            log.info("db exist... openId:" + openId);
            return true;
        }
        return false;
    }

    @Override
    public int saveOrUpdateNickname(UserInfoDTO userInfoDTO) {
        if (StringUtils.isEmpty(userInfoDTO.getNickName())) {
            throw new BaseException("nickname can not be null");
        }
        if (StringUtils.isEmpty(userInfoDTO.getOpenId())) {
            throw new BaseException("openId can not be null");
        }
        if (StringUtils.isEmpty(userInfoDTO.getPhoneNumber())) {
            throw new BaseException("phoneNumber can not be null");
        }
        return userInfoMapper.insertOrUpdate(userInfoDTO);
    }

    @Override
    public UserInfoDTO getDTOByOpenId(UserInfoDTO userInfoDTO) {
        String openId = userInfoDTO.getOpenId();
        if (StringUtils.isEmpty(openId)) {
            throw new BaseException("openId can not be null");
        }
        return userInfoMapper.getDTOByOpenId(openId);
    }

    @Override
    public int updateIsMemberByOpenId(String openId) {
        return userInfoMapper.updateIsMemberByOpenId(openId);
    }

    @Override
    public boolean adjustIsMember(String openId) {
        UserInfoDTO dto = userInfoMapper.getDTOByOpenId(openId);
        return dto != null && dto.getIsMember() == 1;
    }

    @Override
    public int dealAddNotMemberMsgCnt(String openId) {
        userInfoMapper.updateNotMemberMsgCnt(openId);
        UserInfoDTO dto = userInfoMapper.getDTOByOpenId(openId);
        return dto.getNotMemberMsgCnt();
    }


}
