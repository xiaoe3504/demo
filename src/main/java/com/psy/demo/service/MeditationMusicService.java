package com.psy.demo.service;


import com.psy.demo.dto.MeditationMusicDTO;
import com.psy.demo.vo.res.MeditationMusicTypeResVO;

import java.util.List;

public interface MeditationMusicService {

    List<MeditationMusicTypeResVO> select();

}
