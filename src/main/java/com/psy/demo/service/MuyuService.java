package com.psy.demo.service;


import com.psy.demo.vo.res.MuyuCntVO;

public interface MuyuService {

    int insertOrUpdate(String openId);

    MuyuCntVO getCnt(String openId);
}
