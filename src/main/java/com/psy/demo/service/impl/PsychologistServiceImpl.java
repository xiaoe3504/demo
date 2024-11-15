package com.psy.demo.service.impl;

import com.psy.demo.dto.MessageDTO;
import com.psy.demo.dto.PsychologistDTO;
import com.psy.demo.enums.PsyStatusEnum;
import com.psy.demo.global.BaseException;
import com.psy.demo.mapper.MessageMapper;
import com.psy.demo.mapper.PayInfoMapper;
import com.psy.demo.mapper.PsychologistMapper;
import com.psy.demo.service.PsychologistService;
import com.psy.demo.utils.StringUtil;
import com.psy.demo.vo.req.MessageReq;
import com.psy.demo.vo.res.OrderAmountVO;
import com.psy.demo.vo.res.PsychologistOrderResVO;
import com.psy.demo.vo.res.PsychologistVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class PsychologistServiceImpl implements PsychologistService {

    @Autowired
    PsychologistMapper psychologistMapper;
    @Autowired
    PayInfoMapper payInfoMapper;
    @Autowired
    MessageMapper messageMapper;


    @Override
    public int dealAdd(PsychologistDTO dto) {
        return psychologistMapper.insert(dto);
    }

    @Override
    public List<PsychologistVO> selectAll() {
        List<PsychologistDTO> list = psychologistMapper.selectAll();
        return list.stream().map(PsychologistDTO::genPsychologistVOByDTO).collect(Collectors.toList());
    }

    @Override
    public PsychologistOrderResVO selectOrderResByOpenId(String openId) {
        PsychologistOrderResVO vo = psychologistMapper.selectOrderResByOpenId(openId);
        return vo;
    }

    @Override
    public OrderAmountVO getOrderCntAndAmount(String psychologistId) {
        OrderAmountVO vo = payInfoMapper.getOrderCntAndAmount(psychologistId);
        dealAmount(vo);
        return vo;
    }

    @Override
    public int setStatus(PsychologistDTO dto) {
        if (StringUtils.isEmpty(dto.getOpenId())) {
            throw new BaseException("openId can not be null");
        }
        if (null == dto.getStatus()) {
            throw new BaseException("status can not be null");
        }
        if (!PsyStatusEnum.isPsyStatusEnum(dto.getStatus())) {
            throw new BaseException("status invalid");
        }

        return psychologistMapper.setStatus(dto);
    }

    private void dealAmount(OrderAmountVO vo) {
        int amount = vo.getAmount();
        String amount2Decimal = StringUtil.intTo2Decimal(amount);
        vo.setAmountStr(amount2Decimal);
    }



    @Override
    public int getUnreadCnt(MessageReq req) {
        String psychologistId = req.getPsychologistId();
        if (StringUtils.isEmpty(psychologistId)) {
            throw new BaseException("getUnreadCnt error psychologistId null ");
        }
        return messageMapper.selectHasReadCntByPsychologistId(req);
    }
}
