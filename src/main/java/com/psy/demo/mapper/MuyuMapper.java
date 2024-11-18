package com.psy.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MuyuMapper {

    int insertOrUpdate(@Param("openId") String openId);

    Integer getCntToday(@Param("openId") String openId);

    Integer getCntTotal(@Param("openId") String openId);
}