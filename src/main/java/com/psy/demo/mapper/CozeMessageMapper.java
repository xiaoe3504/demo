package com.psy.demo.mapper;

import com.psy.demo.dto.CozeMessageDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CozeMessageMapper {

    int insert(CozeMessageDTO record);

    CozeMessageDTO selectByPrimaryKey(Long id);

}