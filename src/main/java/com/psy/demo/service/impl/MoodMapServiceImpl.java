package com.psy.demo.service.impl;

import com.psy.demo.dto.MoodMapDTO;
import com.psy.demo.enums.MoodEnum;
import com.psy.demo.global.BaseException;
import com.psy.demo.mapper.MoodMapMapper;
import com.psy.demo.service.MoodMapService;
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
    public MoodMapResFinalFinalVO selectByOpenId(String openId) {
        if (StringUtils.isEmpty(openId)) {
            throw new BaseException("openId is null err");
        }
        MoodMapResFinalVO[][] moods = getMoods(openId);
        String moodToday = getMoodToday(openId);
        return MoodMapResFinalFinalVO.builder().moods(moods).moodToday(moodToday).build();
    }

    private MoodMapResFinalVO[][] getMoods(String openId) {
        List<MoodMapResFinalVO> moodsFromDB = getMoodsFromDB(openId);
        List<MoodMapResFinalVO> moodsByCalc = getMoodByCalc();
        List<MoodMapResFinalVO> currentMonth = mergeList(moodsFromDB, moodsByCalc);
        List<MoodMapResFinalVO> lastMonth = getLastMonth();
        List<MoodMapResFinalVO> listFinal = getListFinal(currentMonth, lastMonth);
        return ListUtils.convertListToDoubleArray(listFinal, 7);
    }

    @NotNull
    private List<MoodMapResFinalVO> getMoodByCalc() {
        int daysCnt = DateUtils.getDaysMonth(0);

        List<MoodMapResFinalVO> moodsByCalc = new ArrayList<>();
        for (int i = 1; i < daysCnt + 1; i++) {
            moodsByCalc.add(MoodMapResFinalVO.builder().thisMonth(true).color(MoodEnum.MOOD_DEFAULT.getColor()).date(i).build());
        }
        return moodsByCalc;
    }

    @NotNull
    private List<MoodMapResFinalVO> getMoodsFromDB(String openId) {
        List<MoodMapDTO> list = moodMapMapper.selectByOpenId(openId);
        return list.stream().map(e -> MoodMapResFinalVO.builder().color(MoodEnum.getColorByCode(e.getMood())).thisMonth(true).date(DateUtils.dealDateStringToInt(e.getMoodDate())).build())
                .collect(Collectors.toList());
    }

    @NotNull
    private List<MoodMapResFinalVO> getListFinal(List<MoodMapResFinalVO> currentMonth, List<MoodMapResFinalVO> lastMonth) {
        List<MoodMapResFinalVO> listFinal = new ArrayList<>();
        listFinal.addAll(lastMonth);
        listFinal.addAll(currentMonth);
        int len = listFinal.size() % 7;
        if (len != 0) {
            for (int i = 1; i <= 7 - len; i++) {
                MoodMapResFinalVO vo = MoodMapResFinalVO.builder().thisMonth(false).date(i).build();
                listFinal.add(vo);
            }
        }
        return listFinal;
    }

    @NotNull
    private List<MoodMapResFinalVO> getLastMonth() {
        int firstDay = DateUtils.getFirstDateCurrentMonth();

        List<MoodMapResFinalVO> lastMonth = new ArrayList<>();
        int lastMonthDays = DateUtils.getDaysMonth(-1);
        for (int i = 0; i < firstDay - 1; i++) {
            MoodMapResFinalVO vo = MoodMapResFinalVO.builder().thisMonth(false).date(lastMonthDays - (firstDay - 1 - i - 1)).build();
            lastMonth.add(vo);
        }
        return lastMonth;
    }

    @NotNull
    private List<MoodMapResFinalVO> mergeList(List<MoodMapResFinalVO> moodsFromDB, List<MoodMapResFinalVO> moodsByCalc) {
        // 使用Map以优化查找效率
        Map<Integer, MoodMapResFinalVO> map = new HashMap<>();
        moodsFromDB.forEach(item -> map.put(item.getDate(), item)); // 以list1为准，先放入map
        moodsByCalc.forEach(item -> map.putIfAbsent(item.getDate(), item)); // list2中的元素，如果list1中没有，再放入map

        // 从map的keySet中获取合并后的list
        return new ArrayList<>(map.values());
    }

    @Override
    public String getMoodToday(String openId) {
        if (StringUtils.isEmpty(openId)) {
            throw new BaseException("openId is null err");
        }
        String moodDate = DateUtils.getCurrentDate();
        MoodMapDTO req=new MoodMapDTO();
        req.setMoodDate(moodDate);
        req.setOpenId(openId);
        MoodMapDTO dto=moodMapMapper.getMoodToday(req);
        if (dto==null){
            return MoodEnum.MOOD_DEFAULT.getDesc();
        }
        return MoodEnum.getDescByCode(dto.getMood());
    }


}
