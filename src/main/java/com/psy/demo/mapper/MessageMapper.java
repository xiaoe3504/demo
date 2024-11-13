package com.psy.demo.mapper;

import com.psy.demo.dto.MessageDTO;
import com.psy.demo.vo.req.MessageReq;
import com.psy.demo.vo.res.MessageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageMapper {
    int insert(MessageDTO record);

    List<MessageVO> selectMessagesByOpenId(@Param("req") MessageReq req);

    List<MessageVO> selectMessagesByPsychologistId(@Param("req") MessageReq req);

    int dealHasRead(@Param("dto") MessageDTO dto);

    int selectHasReadCntByPsychologistId(@Param("req") MessageReq req);
}