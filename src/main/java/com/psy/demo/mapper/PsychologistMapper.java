package com.psy.demo.mapper;

import com.psy.demo.dto.PsychologistDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PsychologistMapper {
    int insert(PsychologistDTO record);

    List<PsychologistDTO> selectAll();

}