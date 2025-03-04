package com.psy.demo.mapper;

import com.psy.demo.dto.SurveyRecordDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SurveyRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyRecordDTO record);

    int insertSelective(SurveyRecordDTO record);

    SurveyRecordDTO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyRecordDTO record);

    int updateByPrimaryKey(SurveyRecordDTO record);
}