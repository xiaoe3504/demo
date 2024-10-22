package com.psy.demo.service;


import com.psy.demo.vo.req.CommonTypeReqVO;
import com.psy.demo.vo.res.MeditationMusicTypeResFinalVO;


public interface MeditationMusicService {

    MeditationMusicTypeResFinalVO select(String openId);

    MeditationMusicTypeResFinalVO selectType(CommonTypeReqVO req);
}
