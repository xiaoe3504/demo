package com.psy.demo.service;


import com.psy.demo.dto.UserInfoDTO;

import java.util.List;

public interface UserInfoService {

    int saveUser(UserInfoDTO userInfoDTO);

    int saveOrUpdateNickname(UserInfoDTO userInfoDTO);

    UserInfoDTO getDTOByOpenId(UserInfoDTO userInfoDTO);
}
