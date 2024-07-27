package com.psy.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.psy.demo.dto.IntelligentTestScaleDTO;
import com.psy.demo.mapper.IntelligentTestScaleMapper;
import com.psy.demo.service.IntelligentTestScaleService;
import com.psy.demo.vo.res.IntelligentTestScaleTypeResVO;
import com.psy.demo.vo.res.IntelligentTestScaleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@Slf4j
public class IntelligentTestScaleServiceImpl implements IntelligentTestScaleService {

    @Autowired
    IntelligentTestScaleMapper intelligentTestScaleMapper;

    @Override
    public List<IntelligentTestScaleTypeResVO> select() {
        List<IntelligentTestScaleDTO> list = intelligentTestScaleMapper.select();
        Map<String, List<IntelligentTestScaleDTO>> res = getMapAndSort(list);

        return res.entrySet().stream().map(e -> {
            String type = e.getKey();
            List<IntelligentTestScaleVO> listInner = e.getValue().stream()
                    .map(IntelligentTestScaleVO::genVOByDTO).collect(Collectors.toList());
            IntelligentTestScaleTypeResVO vo = new IntelligentTestScaleTypeResVO();
            vo.setType(type);
            vo.setDataArr(listInner);
            return vo;
        }).collect(Collectors.toList());
    }

    private Map<String, List<IntelligentTestScaleDTO>> getMapAndSort(List<IntelligentTestScaleDTO> list) {
        Map<String, Integer> mapUseKeySort = genMapUseKeySort();
        Map<String, List<IntelligentTestScaleDTO>> map = list.stream().collect(Collectors.groupingBy(IntelligentTestScaleDTO::getType));
        // 将Map的entrySet转换为List
        Map<String, List<IntelligentTestScaleDTO>> treeMap = new TreeMap<>(Comparator.comparing(mapUseKeySort::get));
        treeMap.putAll(map);
        log.info(JSONObject.toJSONString(treeMap));
        return treeMap;
    }

    private Map<String, Integer> genMapUseKeySort() {
        Map<String, Integer> mapUseKeySort = new HashMap<>();
        mapUseKeySort.put("情绪情感篇", 1);
        mapUseKeySort.put("人际关系篇", 2);
        mapUseKeySort.put("人格特质篇", 3);
        mapUseKeySort.put("认知思维篇", 4);
        return mapUseKeySort;
    }
}
