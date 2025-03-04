package com.psy.demo.mapper;

import com.psy.demo.dto.SurveyRecordDetailDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SurveyRecordDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyRecordDetailDTO record);

    int insertSelective(SurveyRecordDetailDTO record);

    SurveyRecordDetailDTO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyRecordDetailDTO record);

    int updateByPrimaryKey(SurveyRecordDetailDTO record);
}