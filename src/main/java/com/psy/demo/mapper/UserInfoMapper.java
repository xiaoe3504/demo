package com.psy.demo.mapper;

import com.psy.demo.dto.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserInfoMapper {
    int deleteById(Long id);

    int deleteByOpenId(String openId);

    int insert(UserInfoDTO record);

    UserInfoDTO selectById(Long id);

    UserInfoDTO selectByOpenId(String openId);

    int insertOrUpdate(UserInfoDTO userInfoDTO);

    UserInfoDTO getDTOByOpenId(String openId);
}