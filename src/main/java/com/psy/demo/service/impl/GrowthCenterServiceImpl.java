package com.psy.demo.service.impl;

import com.psy.demo.dto.GrowthCenterDTO;
import com.psy.demo.enums.GrowthCenterTypeEnum;
import com.psy.demo.global.BaseException;
import com.psy.demo.mapper.GrowthCenterMapper;
import com.psy.demo.service.GrowthCenterService;
import com.psy.demo.utils.MyConstantString;
import com.psy.demo.utils.StringUtil;
import com.psy.demo.vo.res.GrowthCenterInnerVO;
import com.psy.demo.vo.res.GrowthCenterVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class GrowthCenterServiceImpl implements GrowthCenterService {
    @Autowired
    GrowthCenterMapper growthCenterMapper;

    @Override
    public GrowthCenterVO queryByType(String type) {
        GrowthCenterVO vo=new GrowthCenterVO();

        if(!GrowthCenterTypeEnum.isGrowthCenterType(type)){
            throw new BaseException("GrowthCenterTypeEnum not match");
        }

        List<GrowthCenterDTO> list = growthCenterMapper.queryByType(type);
        if (CollectionUtils.isEmpty(list)){
            return vo;
        }
        List<GrowthCenterInnerVO> res = new ArrayList<>();
        String introduce = list.get(0).getIntroduce();
        vo.setIntroduce(introduce);
        for (int i = 0; i < list.size(); i++) {
            GrowthCenterInnerVO voInner = GrowthCenterInnerVO.builder().id(String.format("%02d", i+1))
                    .duration(list.get(i).getDuration()).name(list.get(i).getName())
                    .isPlay(false)
                    .src(StringUtil.getSrc(list.get(i).getType(),list.get(i).getName()))
                    .build();
            res.add(voInner);
        }
        vo.setList(res);
        return vo;

    }
}
