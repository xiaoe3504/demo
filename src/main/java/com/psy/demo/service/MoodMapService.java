package com.psy.demo.service;


import com.psy.demo.dto.MoodMapDTO;
import com.psy.demo.vo.res.MoodMapResVO;

import java.util.List;

public interface MoodMapService {

    int dealAdd(MoodMapDTO moodMapDTO);

    List<MoodMapResVO> selectByOpenId(String openId);

}
