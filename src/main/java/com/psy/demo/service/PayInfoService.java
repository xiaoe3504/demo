package com.psy.demo.service;


import com.psy.demo.dto.PayInfoDTO;
import com.psy.demo.vo.res.PayInfoFinalVO;
import com.psy.demo.vo.res.PayInfoVO;

import java.util.List;


public interface PayInfoService {

    int insert(PayInfoDTO payInfoDTO);

    List<PayInfoVO> selectByOpenId(PayInfoDTO dto);

    List<PayInfoFinalVO> select(String openId);
}
