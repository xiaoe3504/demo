package com.psy.demo.service.impl;

import com.psy.demo.dto.MoodMapDTO;
import com.psy.demo.enums.MoodEnum;
import com.psy.demo.global.BaseException;
import com.psy.demo.mapper.MoodMapMapper;
import com.psy.demo.service.MoodMapService;
import com.psy.demo.utils.DateUtils;
import com.psy.demo.vo.res.MoodMapResFinalVO;
import com.psy.demo.vo.res.MoodMapResVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


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
    public List<MoodMapResFinalVO> selectByOpenId(String openId) {
        if (StringUtils.isEmpty(openId)) {
            throw new BaseException("openId is null err");
        }
        List<MoodMapDTO> list = moodMapMapper.selectByOpenId(openId);
        List<MoodMapResFinalVO> resMoods = list.stream().map(e -> MoodMapResFinalVO.builder().color(e.getMood()).date(DateUtils.dealDateStringToInt(e.getMoodDate())).build())
                .collect(Collectors.toList());
        int daysCnt = DateUtils.getDaysCurrentMonth();

        List<MoodMapResFinalVO> resFinal = new ArrayList<>();
        for (int i = 1; i < daysCnt + 1; i++) {
            resFinal.add(MoodMapResFinalVO.builder().color(0).date(i).build());
        }

        Map<Integer, MoodMapResFinalVO> mergedMap = Stream.concat(resMoods.stream(), resFinal.stream())
                .collect(Collectors.toMap(
                        MoodMapResFinalVO::getDate, // keyMapper，用作唯一键
                        e -> e, // valueMapper，保留person对象
                        (e1, e2) -> MoodMapResFinalVO.builder().color(e1.getColor()).thisMonth(true).date(e1.getDate()).build()
                        // mergeFunction，用于合并具有相同键的值
                ));

        List<MoodMapResFinalVO> mergedList = new ArrayList<>(mergedMap.values());

        return mergedList;
    }
}
