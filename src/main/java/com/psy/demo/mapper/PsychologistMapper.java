package com.psy.demo.mapper;

import com.psy.demo.dto.PsychologistDTO;
import com.psy.demo.vo.res.PsychologistOrderResVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PsychologistMapper {
    int insert(PsychologistDTO record);

    List<PsychologistDTO> selectAll();

    PsychologistOrderResVO selectOrderResByOpenId(@Param("openId") String openId);

    int setStatus(@Param("dto") PsychologistDTO dto);
}