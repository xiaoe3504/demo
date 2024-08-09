package com.psy.demo.mapper;

import com.psy.demo.dto.UserInfoCategoryCntDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoCategoryCntMapper {
    int insertOrUpdateCnt(UserInfoCategoryCntDTO record);

    int selectCntByUK(UserInfoCategoryCntDTO dto);

}