package com.psy.demo.mapper;

import com.psy.demo.dto.ProfitSharingInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProfitSharingInfoMapper {
    int insert(@Param("dto")ProfitSharingInfoDTO dto);
    int updateOutOrderNoOrderId(@Param("dto") ProfitSharingInfoDTO dto);
}