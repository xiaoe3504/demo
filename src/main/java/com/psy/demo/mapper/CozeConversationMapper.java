package com.psy.demo.mapper;

import com.psy.demo.dto.CozeConversationDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CozeConversationMapper {

    int insertOrUpdate(CozeConversationDTO record);

    CozeConversationDTO selectByPrimaryKey(Long id);

}