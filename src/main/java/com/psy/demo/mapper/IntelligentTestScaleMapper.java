package com.psy.demo.mapper;

import com.psy.demo.dto.IntelligentTestScaleDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IntelligentTestScaleMapper {


    List<IntelligentTestScaleDTO> select(String type);

}