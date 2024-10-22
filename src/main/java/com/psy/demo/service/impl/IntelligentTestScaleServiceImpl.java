package com.psy.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.psy.demo.dto.IntelligentTestScaleDTO;
import com.psy.demo.dto.UserInfoDTO;
import com.psy.demo.enums.IntelligentTestTypeEnum;
import com.psy.demo.enums.MeditationMusicTypeEnum;
import com.psy.demo.global.BaseException;
import com.psy.demo.mapper.IntelligentTestScaleMapper;
import com.psy.demo.mapper.PayInfoMapper;
import com.psy.demo.mapper.UserInfoMapper;
import com.psy.demo.service.IntelligentTestScaleService;
import com.psy.demo.vo.req.CommonTypeReqVO;
import com.psy.demo.vo.res.IntelligentTestScaleTypeResFinalVO;
import com.psy.demo.vo.res.IntelligentTestScaleTypeResVO;
import com.psy.demo.vo.res.IntelligentTestScaleVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.psy.demo.enums.IntelligentTestTypeEnum.genMapUseKeySort;
import static com.psy.demo.utils.MyConstantString.DEFAULT_NULL_STRING;


@Service
@Slf4j
public class IntelligentTestScaleServiceImpl implements IntelligentTestScaleService {

    @Autowired
    IntelligentTestScaleMapper intelligentTestScaleMapper;
    @Autowired
    PayInfoMapper payInfoMapper;
    @Autowired
    UserInfoMapper userInfoMapper;

    static Map<String, Integer> mapUseKeySort;

    static {
        mapUseKeySort= genMapUseKeySort();
    }

    @Override
    public IntelligentTestScaleTypeResFinalVO select(String openId) {
        IntelligentTestScaleTypeResFinalVO resFinal = new IntelligentTestScaleTypeResFinalVO();

        List<IntelligentTestScaleDTO> list = intelligentTestScaleMapper.select(DEFAULT_NULL_STRING);
        Map<String, List<IntelligentTestScaleDTO>> res = getMapAndSort(list);

        List<IntelligentTestScaleTypeResVO> listRes = res.entrySet().stream().map(e -> {
            String type = e.getKey();
            List<IntelligentTestScaleVO> listInner = e.getValue().stream()
                    .map(IntelligentTestScaleVO::genVOByDTO).collect(Collectors.toList());
            IntelligentTestScaleTypeResVO vo = new IntelligentTestScaleTypeResVO();
            vo.setType(type + "篇");
            vo.setDataArr(listInner);
            return vo;
        }).collect(Collectors.toList());
        resFinal.setList(listRes);
        if (getIsMember(openId)) {
            resFinal.setMember(true);
        }
        return resFinal;
    }

    private boolean getIsMember(String openId) {
        UserInfoDTO userInfoDTO = userInfoMapper.getDTOByOpenId(openId);
        return userInfoDTO != null && userInfoDTO.getIsMember() == 1;
    }

    @Override
    public IntelligentTestScaleTypeResFinalVO selectType(CommonTypeReqVO req) {
        IntelligentTestScaleTypeResFinalVO resFinal = new IntelligentTestScaleTypeResFinalVO();

        String openId = req.getOpenId();
        int type = req.getType();
        if (StringUtils.isEmpty(openId)) {
            throw new BaseException("openId can not be null");
        }
        if (type <= 0) {
            throw new BaseException("type invalid");
        }

        String typeDesc = IntelligentTestTypeEnum.getDescByCode(type);
        List<IntelligentTestScaleDTO> list = intelligentTestScaleMapper.select(typeDesc);
        List<IntelligentTestScaleVO> listVO = list.stream().map(IntelligentTestScaleVO::genVOByDTO).collect(Collectors.toList());

        IntelligentTestScaleTypeResVO vo = new IntelligentTestScaleTypeResVO();
        vo.setType(typeDesc + "篇");
        vo.setDataArr(listVO);

        List<IntelligentTestScaleTypeResVO> listRes = Collections.singletonList(vo);
        resFinal.setList(listRes);
        if (getIsMember(openId)) {
            resFinal.setMember(true);
        }
        return resFinal;
    }

    private Map<String, List<IntelligentTestScaleDTO>> getMapAndSort(List<IntelligentTestScaleDTO> list) {
        Map<String, List<IntelligentTestScaleDTO>> map = list.stream().collect(Collectors.groupingBy(IntelligentTestScaleDTO::getType));
        // 将Map的entrySet转换为List
        Map<String, List<IntelligentTestScaleDTO>> treeMap = new TreeMap<>(Comparator.comparing(mapUseKeySort::get));
        treeMap.putAll(map);
        log.info(JSONObject.toJSONString(treeMap));
        return treeMap;
    }


}
