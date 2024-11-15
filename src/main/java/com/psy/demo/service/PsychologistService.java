package com.psy.demo.service;


import com.psy.demo.dto.MessageDTO;
import com.psy.demo.dto.PsychologistDTO;
import com.psy.demo.vo.req.MessageReq;
import com.psy.demo.vo.res.OrderAmountVO;
import com.psy.demo.vo.res.PsychologistOrderResVO;
import com.psy.demo.vo.res.PsychologistVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface PsychologistService {

    int dealAdd(PsychologistDTO dto);


    List<PsychologistVO> selectAll();

    PsychologistOrderResVO selectOrderResByOpenId(String openId);

    OrderAmountVO getOrderCntAndAmount(@Param("psychologistId") String psychologistId);

    int setStatus(PsychologistDTO dto);


    int getUnreadCnt(MessageReq req);
}
