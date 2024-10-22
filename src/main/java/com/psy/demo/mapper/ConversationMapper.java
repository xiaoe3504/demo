package com.psy.demo.mapper;

import com.psy.demo.dto.ConversationDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ConversationMapper {
    int insert(ConversationDTO record);

    ConversationDTO selectById2(@Param("dto") ConversationDTO dto);

}