package com.psy.demo.service;


import com.psy.demo.dto.MoodMapDTO;
import com.psy.demo.vo.res.MoodMapResFinalFinalVO;
import com.psy.demo.vo.res.MoodMapResFinalVO;


public interface MoodMapService {

    int dealAdd(MoodMapDTO moodMapDTO);

    MoodMapResFinalFinalVO selectByOpenId(String openId);

    String getMoodToday(String openId);
}
