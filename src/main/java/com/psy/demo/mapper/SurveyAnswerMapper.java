package com.psy.demo.mapper;

import com.psy.demo.dto.SurveyAnswerDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SurveyAnswerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyAnswerDTO record);

    int insertSelective(SurveyAnswerDTO record);

    SurveyAnswerDTO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyAnswerDTO record);

    int updateByPrimaryKey(SurveyAnswerDTO record);
}