package com.psy.demo.mapper;

import com.psy.demo.dto.IntelligentTestScaleDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IntelligentTestScaleMapper {


    List<IntelligentTestScaleDTO> select(@Param("type") String type);

}