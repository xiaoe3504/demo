package com.psy.demo.service.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.psy.demo.dto.IntelligentTestScaleDTO;
import com.psy.demo.dto.MeditationMusicDTO;
import com.psy.demo.dto.PayInfoDTO;
import com.psy.demo.enums.FeedbackEnum;
import com.psy.demo.enums.PayCategoryEnum;
import com.psy.demo.global.BaseException;
import com.psy.demo.mapper.IntelligentTestScaleMapper;
import com.psy.demo.mapper.MeditationMusicMapper;
import com.psy.demo.mapper.PayInfoMapper;
import com.psy.demo.service.PayInfoService;
import com.psy.demo.utils.MyConstantString;
import com.psy.demo.vo.req.FeedbackReq;
import com.psy.demo.vo.res.PayInfoFinalVO;
import com.psy.demo.vo.res.PayInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PayInfoServiceImpl implements PayInfoService {

    @Autowired
    PayInfoMapper payInfoMapper;

    @Autowired
    IntelligentTestScaleMapper intelligentTestScaleMapper;
    @Autowired
    MeditationMusicMapper meditationMusicMapper;

    private static final String TYPE_TEST = "TYPE_TEST";
    private static final String TYPE_MEDITATION = "TYPE_MEDITATION";

    Map<String, String> mapTypeTest;
    Map<String, String> mapTypeMeditation;

    private final LoadingCache<String, Map<String, String>> cacheTypeName = CacheBuilder.newBuilder()
            .maximumSize(10) // 最多缓存100个键值对
            .expireAfterWrite(10, TimeUnit.DAYS) // 写入后10天过期
            .build(
                    new CacheLoader<String, Map<String, String>>() {
                        @NotNull
                        public Map<String, String> load(@NotNull String key) {
                            if (key.equals(TYPE_TEST)) {
                                return intelligentTestScaleMapper.select(MyConstantString.DEFAULT_NULL_STRING).stream().collect(Collectors.toMap
                                        (dto -> String.valueOf(dto.getId()), IntelligentTestScaleDTO::getName));
                            } else if (key.equals(TYPE_MEDITATION)) {
                                return meditationMusicMapper.select(MyConstantString.DEFAULT_NULL_STRING).stream().collect(Collectors.toMap
                                        (dto -> String.valueOf(dto.getId()), MeditationMusicDTO::getName));
                            }
                            return new HashMap<>();
                        }
                    }
            );

    @PostConstruct
    public void init() {
        try {
            mapTypeTest = cacheTypeName.get(TYPE_TEST);
            mapTypeMeditation = cacheTypeName.get(TYPE_MEDITATION);
        } catch (ExecutionException e) {
            log.error("get cache error:" + e.getMessage(), e);
            throw new BaseException("get cache error");
        }
    }

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
        if (StringUtils.isEmpty(payInfoDTO.getAmount())) {
            throw new BaseException("Amount is null err");
        }
        if (StringUtils.isEmpty(payInfoDTO.getTradeNo())) {
            throw new BaseException("TradeNo is null err");
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
                .map(e -> {
                    PayInfoVO vo = PayInfoVO.builder().category(e.getKey()).dtos(e.getValue()).build();
                    vo.getDtos().forEach(ee -> {
                        String idString = String.valueOf(ee.getId());
                        if (e.getKey().equals(TYPE_TEST)) {
                            ee.setName(mapTypeTest.get(idString));
                        } else if (e.getKey().equals(TYPE_MEDITATION)) {
                            ee.setName(mapTypeMeditation.get(idString));
                        }
                    });
                    return vo;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<PayInfoFinalVO> select(String openId) {
        List<PayInfoDTO> list = payInfoMapper.selectByOpenId(openId);
        return list.stream().map(PayInfoFinalVO::genVO).collect(Collectors.toList());
    }

    @Override
    public int dealFeedback(FeedbackReq req) {
        if (null == req.getId() || req.getId() <= 0) {
            throw new BaseException("id null invalid err");
        }
        if (!FeedbackEnum.isFeedBackType(req.getFeedback())) {
            throw new BaseException("feedback err");
        }
        return payInfoMapper.updateFeedback(req);
    }


}
