package com.psy.demo.service.impl;

import com.psy.demo.dto.MoodMapDTO;
import com.psy.demo.dto.PsychologistDTO;
import com.psy.demo.enums.MoodEnum;
import com.psy.demo.global.BaseException;
import com.psy.demo.mapper.MoodMapMapper;
import com.psy.demo.mapper.PsychologistMapper;
import com.psy.demo.service.MoodMapService;
import com.psy.demo.service.PsychologistService;
import com.psy.demo.utils.DateUtils;
import com.psy.demo.utils.ListUtils;
import com.psy.demo.vo.res.MoodMapResFinalFinalVO;
import com.psy.demo.vo.res.MoodMapResFinalVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
}
