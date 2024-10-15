package com.psy.demo.service.impl;

import com.psy.demo.dto.MoodMapDTO;
import com.psy.demo.enums.MoodEnum;
import com.psy.demo.global.BaseException;
import com.psy.demo.mapper.MoodMapMapper;
import com.psy.demo.service.MoodMapService;
import com.psy.demo.utils.DateUtils;
import com.psy.demo.vo.res.MoodMapResVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class MoodMapServiceImpl implements MoodMapService {

    @Autowired
    MoodMapMapper moodMapMapper;


    @Override
    public int dealAdd(MoodMapDTO moodMapDTO) {
        String openId = moodMapDTO.getOpenId();
        if (StringUtils.isEmpty(openId)) {
            throw new BaseException("openId is null err");
        }
        int mood = moodMapDTO.getMood();
        if (!MoodEnum.isMoodType(mood)) {
            throw new BaseException("mood not valid");
        }
        String moodDate = DateUtils.getCurrentDate();
        moodMapDTO.setMoodDate(moodDate);
        return moodMapMapper.insertOrUpdate(moodMapDTO);

    }

    @Override
    public List<MoodMapResVO> selectByOpenId(String openId) {
        if (StringUtils.isEmpty(openId)) {
            throw new BaseException("openId is null err");
        }
        List<MoodMapDTO> list = moodMapMapper.selectByOpenId(openId);
        return list.stream().map(e -> MoodMapResVO.builder().mood(e.getMood()).moodDate(e.getMoodDate()).build())
                .collect(Collectors.toList());
    }
}
