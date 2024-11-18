package com.psy.demo.service.impl;

import com.psy.demo.global.BaseException;
import com.psy.demo.mapper.MuyuMapper;
import com.psy.demo.service.MuyuService;
import com.psy.demo.vo.res.MuyuCntVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class MuyuServiceImpl implements MuyuService {

    @Autowired
    MuyuMapper muyuMapper;


    @Override
    public int insertOrUpdate(String openId) {
        if (StringUtils.isEmpty(openId)) {
            throw new BaseException("openId can not be null");
        }
        return muyuMapper.insertOrUpdate(openId);
    }

    @Override
    public MuyuCntVO getCnt(String openId) {

        return MuyuCntVO.builder()
                .dayCnt(getTodayCnt(openId))
                .totalCnt(getTotalCnt(openId))
                .build();
    }

    public int getTodayCnt(String openId) {
        Integer cnt = muyuMapper.getCntToday(openId);
        return cnt == null ? 0 : cnt;
    }

    public int getTotalCnt(String openId) {
        Integer cnt = muyuMapper.getCntTotal(openId);
        return cnt == null ? 0 : cnt;
    }
}
