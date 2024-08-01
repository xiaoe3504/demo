package com.psy.demo.service.impl;

import com.psy.demo.dto.PayInfoDTO;
import com.psy.demo.enums.PayCategoryEnum;
import com.psy.demo.global.BaseException;
import com.psy.demo.mapper.PayInfoMapper;
import com.psy.demo.service.PayInfoService;
import com.psy.demo.vo.res.PayInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PayInfoServiceImpl implements PayInfoService {

    @Autowired
    PayInfoMapper payInfoMapper;


    @Override
    public int insert(PayInfoDTO payInfoDTO) {
        if (StringUtils.isEmpty(payInfoDTO.getOpenId())) {
            throw new BaseException("OpenId is null err");
        }
        if (StringUtils.isEmpty(payInfoDTO.getCategory())) {
            throw new BaseException("Category is null err");
        }
        if (!PayCategoryEnum.isCategoryType(payInfoDTO.getCategory())) {
            throw new BaseException("Category wrong enum err");
        }
        if (StringUtils.isEmpty(payInfoDTO.getUniId())) {
            throw new BaseException("UniId is null err");
        }
        int res;
        try {
            res = payInfoMapper.insert(payInfoDTO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException("payInfoMapper.insert err");
        }
        return res;
    }

    @Override
    public List<PayInfoVO> selectByOpenId(PayInfoDTO dto) {
        List<PayInfoDTO> list = payInfoMapper.selectByUk3(dto);
        return list.stream().collect(Collectors.groupingBy(PayInfoDTO::getCategory))
                .entrySet().stream()
                .map(e -> PayInfoVO.builder().category(e.getKey()).dtos(e.getValue()).build())
                .collect(Collectors.toList());
    }
}
