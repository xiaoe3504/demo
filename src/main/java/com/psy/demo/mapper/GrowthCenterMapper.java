package com.psy.demo.mapper;

import com.psy.demo.dto.GrowthCenterDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GrowthCenterMapper {
    List<GrowthCenterDTO> queryByType(@Param("type") String type);
}