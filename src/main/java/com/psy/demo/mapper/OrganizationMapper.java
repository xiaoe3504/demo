package com.psy.demo.mapper;

import com.psy.demo.dto.OrganizationDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrganizationMapper {

    int insert(OrganizationDTO record);

    OrganizationDTO selectByPrimaryKey(Long id);

    List<OrganizationDTO>queryAll();
}