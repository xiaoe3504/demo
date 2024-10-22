package com.psy.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.psy.demo.dto.MeditationMusicDTO;
import com.psy.demo.dto.UserInfoDTO;
import com.psy.demo.enums.MeditationMusicTypeEnum;
import com.psy.demo.global.BaseException;
import com.psy.demo.mapper.MeditationMusicMapper;
import com.psy.demo.mapper.PayInfoMapper;
import com.psy.demo.mapper.UserInfoMapper;
import com.psy.demo.service.MeditationMusicService;
import com.psy.demo.utils.MyConstantString;
import com.psy.demo.vo.req.CommonTypeReqVO;
import com.psy.demo.vo.res.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.psy.demo.enums.MeditationMusicTypeEnum.genMapUseKeySort;


@Service
@Slf4j
public class MeditationMusicServiceImpl implements MeditationMusicService {


    @Autowired
    MeditationMusicMapper meditationMusicMapper;
    @Autowired
    PayInfoMapper payInfoMapper;
    @Autowired
    UserInfoMapper userInfoMapper;

    static Map<String, Integer> mapUseKeySort;

    static {
        mapUseKeySort = genMapUseKeySort();
    }

    @Override
    public MeditationMusicTypeResFinalVO select(String openId) {
        List<MeditationMusicDTO> list = meditationMusicMapper.select(MyConstantString.DEFAULT_NULL_STRING);
        Map<String, List<MeditationMusicDTO>> res = getMapAndSort(list);

        List<MeditationMusicTypeResVO> listRes = res.entrySet().stream().map(e -> {
            String type = e.getKey();
            List<MeditationMusicVO> listInner = e.getValue().stream()
                    .map(MeditationMusicVO::genVOByDTO).collect(Collectors.toList());
            MeditationMusicTypeResVO vo = new MeditationMusicTypeResVO();
            vo.setType(type + "篇");
            vo.setDataArr(listInner);
            return vo;
        }).collect(Collectors.toList());

        MeditationMusicTypeResFinalVO resFinal = new MeditationMusicTypeResFinalVO();
        resFinal.setList(listRes);
        boolean isMember = getIsMember(openId);
        if (isMember) {
            resFinal.setMember(true);
        }
        return resFinal;
    }

    private boolean getIsMember(String openId) {
        UserInfoDTO userInfoDTO = userInfoMapper.getDTOByOpenId(openId);
        return userInfoDTO != null && userInfoDTO.getIsMember() == 1;
    }

    @Override
    public MeditationMusicTypeResFinalVO selectType(CommonTypeReqVO req) {
        MeditationMusicTypeResFinalVO resFinal = new MeditationMusicTypeResFinalVO();
        String openId = req.getOpenId();
        int type = req.getType();
        if (StringUtils.isEmpty(openId)) {
            throw new BaseException("openId can not be null");
        }
        if (type <= 0) {
            throw new BaseException("type invalid");
        }
        String typeDesc = MeditationMusicTypeEnum.getDescByCode(type);
        List<MeditationMusicDTO> list = meditationMusicMapper.select(typeDesc);
        List<MeditationMusicVO> listVO = list.stream().map(MeditationMusicVO::genVOByDTO).collect(Collectors.toList());
        MeditationMusicTypeResVO resVO = new MeditationMusicTypeResVO();
        resVO.setDataArr(listVO);
        resVO.setType(typeDesc + "篇");
        resFinal.setList(Collections.singletonList(resVO));
        boolean isMember = getIsMember(openId);
        if (isMember) {
            resFinal.setMember(true);
        }
        return resFinal;
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
