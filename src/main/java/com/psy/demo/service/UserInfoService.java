package com.psy.demo.service;


import com.psy.demo.dto.UserInfoDTO;

import java.util.List;

public interface UserInfoService {

    int dealAdd(String openId);

    int saveOrUpdateNickname(UserInfoDTO userInfoDTO);

    UserInfoDTO getDTOByOpenId(UserInfoDTO userInfoDTO);

    int updateIsMemberByOpenId(String openId);
}
