package com.psy.demo.mapper;

import com.psy.demo.dto.PayInfoDTO;
import com.psy.demo.vo.req.FeedbackReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PayInfoMapper {
    int insert(PayInfoDTO record);

    PayInfoDTO selectByUk3(PayInfoDTO info);

    List<PayInfoDTO> selectByOpenId(@Param("openId") String openId);

    int updateFeedback(@Param("req") FeedbackReq req);
}