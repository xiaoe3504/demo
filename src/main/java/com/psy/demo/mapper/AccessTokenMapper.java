package com.psy.demo.mapper;

import com.psy.demo.dto.AccessTokenDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccessTokenMapper {

    int insertOrUpdate(AccessTokenDTO record);

    AccessTokenDTO selectByType(@Param("type") String type);

}