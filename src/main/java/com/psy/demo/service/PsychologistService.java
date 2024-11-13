package com.psy.demo.service;


import com.psy.demo.dto.PsychologistDTO;
import com.psy.demo.vo.res.PsychologistOrderResVO;
import com.psy.demo.vo.res.PsychologistVO;

import java.util.List;


public interface PsychologistService {

    int dealAdd(PsychologistDTO dto);


    List<PsychologistVO> selectAll();

    PsychologistOrderResVO selectOrderResByOpenId(String openId);
}
