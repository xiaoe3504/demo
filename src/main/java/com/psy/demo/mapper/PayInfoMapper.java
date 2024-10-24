package com.psy.demo.mapper;

import com.psy.demo.dto.PayInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PayInfoMapper {
    int insert(PayInfoDTO record);

    List<PayInfoDTO> selectByUk3(PayInfoDTO info);

    List<PayInfoDTO> selectByOpenId(@Param("openId") String openId);
}