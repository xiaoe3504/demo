package com.psy.demo.service;


import com.psy.demo.dto.UserInfoDTO;

public interface UserInfoService {

    int saveUser(UserInfoDTO userInfoDTO);

    int saveOrUpdateNickname(UserInfoDTO userInfoDTO);

}
