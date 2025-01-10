package com.psy.demo.service;


import com.psy.demo.dto.UserInfoDTO;
import com.psy.demo.vo.req.UserInfoVO;

import java.util.List;

public interface UserInfoService {

    int dealAdd(String openId);

    int saveOrUpdateNickname(UserInfoDTO userInfoDTO);

    UserInfoDTO getDTOByOpenId(UserInfoDTO userInfoDTO);

    int updateIsMemberByOpenId(String openId);

    boolean adjustIsMember(String openId);

    int dealAddNotMemberMsgCnt(String openId);

    int saveOrUpdateUserInfoReg(UserInfoVO userInfoDTO);
}
