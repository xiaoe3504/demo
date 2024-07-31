package com.psy.demo.service;


import com.psy.demo.dto.PayInfoDTO;
import com.psy.demo.vo.res.PayInfoVO;

import java.util.List;


public interface PayInfoService {

    int insert(PayInfoDTO payInfoDTO);

    List<PayInfoVO> selectByOpenId(String openId);

}
