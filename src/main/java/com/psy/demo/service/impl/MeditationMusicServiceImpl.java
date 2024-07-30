package com.psy.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.psy.demo.dto.IntelligentTestScaleDTO;
import com.psy.demo.dto.MeditationMusicDTO;
import com.psy.demo.mapper.IntelligentTestScaleMapper;
import com.psy.demo.mapper.MeditationMusicMapper;
import com.psy.demo.service.IntelligentTestScaleService;
import com.psy.demo.service.MeditationMusicService;
import com.psy.demo.vo.res.IntelligentTestScaleTypeResVO;
import com.psy.demo.vo.res.IntelligentTestScaleVO;
import com.psy.demo.vo.res.MeditationMusicTypeResVO;
import com.psy.demo.vo.res.MeditationMusicVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@Slf4j
public class MeditationMusicServiceImpl implements MeditationMusicService {


    @Autowired
    MeditationMusicMapper meditationMusicMapper;
    static Map<String, Integer> mapUseKeySort;
    static {
        mapUseKeySort = new HashMap<>();
        mapUseKeySort.put("大自然篇", 1);
        mapUseKeySort.put("身体扫描篇", 2);
        mapUseKeySort.put("呼吸觉察篇", 3);
        mapUseKeySort.put("职场篇", 4);
        mapUseKeySort.put("日常生活篇", 5);
    }

    @Override
    public List<MeditationMusicTypeResVO> select() {
        List<MeditationMusicDTO> list = meditationMusicMapper.select();
        Map<String, List<MeditationMusicDTO>> res = getMapAndSort(list);

        return res.entrySet().stream().map(e -> {
            String type = e.getKey();
            List<MeditationMusicVO> listInner = e.getValue().stream()
                    .map(MeditationMusicVO::genVOByDTO).collect(Collectors.toList());
            MeditationMusicTypeResVO vo = new MeditationMusicTypeResVO();
            vo.setType(type);
            vo.setDataArr(listInner);
            return vo;
        }).collect(Collectors.toList());
    }

    private Map<String, List<MeditationMusicDTO>> getMapAndSort(List<MeditationMusicDTO> list) {
        Map<String, List<MeditationMusicDTO>> map = list.stream().collect(Collectors.groupingBy(MeditationMusicDTO::getType));
        // 将Map的entrySet转换为List
        Map<String, List<MeditationMusicDTO>> treeMap = new TreeMap<>(Comparator.comparing(mapUseKeySort::get));
        treeMap.putAll(map);
        log.info(JSONObject.toJSONString(treeMap));
        return treeMap;
    }

}
