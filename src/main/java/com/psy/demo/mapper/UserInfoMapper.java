package com.psy.demo.mapper;

import com.psy.demo.dto.UserInfoDTO;
import com.psy.demo.vo.req.UserInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserInfoMapper {
    int deleteByOpenId(@Param("openId") String openId);

    int insert(UserInfoDTO record);

    int insertOrUpdate(UserInfoDTO userInfoDTO);

    UserInfoDTO getDTOByOpenId(String openId);

    int updateIsMemberByOpenId(String openId);

    int dealIsMemberExpiresTime();

    int initNotMemberMsgCnt();

    int updateNotMemberMsgCnt(String openId);

    int insertOrUpdateAvatarPhoneNickRealOrg(UserInfoVO userInfoDTO);
}