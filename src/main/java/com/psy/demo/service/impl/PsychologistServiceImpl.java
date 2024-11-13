package com.psy.demo.service.impl;

import com.psy.demo.dto.PsychologistDTO;
import com.psy.demo.mapper.PsychologistMapper;
import com.psy.demo.service.PsychologistService;
import com.psy.demo.vo.res.PsychologistOrderResVO;
import com.psy.demo.vo.res.PsychologistVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class PsychologistServiceImpl implements PsychologistService {

    @Autowired
    PsychologistMapper psychologistMapper;

    @Override
    public int dealAdd(PsychologistDTO dto) {
        return psychologistMapper.insert(dto);
    }

    @Override
    public List<PsychologistVO> selectAll() {
        List<PsychologistDTO> list = psychologistMapper.selectAll();
        return list.stream().map(PsychologistDTO::genPsychologistVOByDTO).collect(Collectors.toList());
    }

    @Override
    public PsychologistOrderResVO selectOrderResByOpenId(String openId) {
        PsychologistOrderResVO vo= psychologistMapper.selectOrderResByOpenId(openId);
        return vo;
    }
}
