package com.psy.demo.service;


import com.psy.demo.dto.PayInfoDTO;
import com.psy.demo.vo.req.FeedbackReq;
import com.psy.demo.vo.res.PayInfoFinalVO;

import java.util.List;


public interface PayInfoService {

    int insert(PayInfoDTO payInfoDTO);

    List<PayInfoFinalVO> select(String openId);

    int dealFeedback(FeedbackReq req);
}
