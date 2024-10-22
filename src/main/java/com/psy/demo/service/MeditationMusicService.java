package com.psy.demo.service;


import com.psy.demo.vo.req.CommonTypeReq;
import com.psy.demo.vo.res.MeditationMusicTypeResFinalVO;


public interface MeditationMusicService {

    MeditationMusicTypeResFinalVO select(String openId);

    MeditationMusicTypeResFinalVO selectType(CommonTypeReq req);
}
