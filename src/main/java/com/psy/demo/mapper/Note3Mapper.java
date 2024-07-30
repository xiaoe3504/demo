package com.psy.demo.mapper;

import com.psy.demo.dto.Note3DTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Note3Mapper {

    int insert(Note3DTO record);

    List<Note3DTO> selectByOpenId(String openId);

}