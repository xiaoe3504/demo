package com.psy.demo.service.impl;

import com.psy.demo.dto.Note3DTO;
import com.psy.demo.mapper.Note3Mapper;
import com.psy.demo.service.Note3Service;
import com.psy.demo.vo.res.Note3VO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Slf4j
public class Note3ServiceImpl implements Note3Service {

    @Autowired
    Note3Mapper note3Mapper;

    static Map<String, String> mapNameType;
    static Map<String, String> mapTypeLabel;

    static {
        mapNameType = new HashMap<>();
        mapNameType.put("绝对化要求，非黑即白", "1");
        mapNameType.put("过分概括化，以偏概全", "2");
        mapNameType.put("灾难化，糟糕至极", "3");

        mapTypeLabel = new HashMap<>();
        mapTypeLabel.put( "1","absolutization");
        mapTypeLabel.put( "2","generalization");
        mapTypeLabel.put( "3","catastrophic");
    }

    @Override
    public int insert(Note3DTO note3DTO) {
        String analysis = note3DTO.getAnalysis();
        String type = mapNameType.getOrDefault(analysis, "0");
        note3DTO.setAnalysisType(type);
        return note3Mapper.insert(note3DTO);
    }

    @Override
    public List<Note3VO> selectByOpenId(String openId) {
        if (StringUtils.isEmpty(openId)) {
            return new ArrayList<>();
        }
        List<Note3DTO> note3DTOS = note3Mapper.selectByOpenId(openId);
        return note3DTOS.stream().map(Note3VO::genVOByDTO).collect(Collectors.toList());
    }
}
