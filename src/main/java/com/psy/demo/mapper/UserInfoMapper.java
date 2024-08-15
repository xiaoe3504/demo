package com.psy.demo.mapper;

import com.psy.demo.dto.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserInfoMapper {
    int deleteByOpenId(String openId);

    int insert(UserInfoDTO record);

    int insertOrUpdate(UserInfoDTO userInfoDTO);

    UserInfoDTO getDTOByOpenId(String openId);

    int updateIsMemberByOpenId(String openId);
}