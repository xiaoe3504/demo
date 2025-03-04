package com.psy.demo.mapper;

import com.psy.demo.dto.SurveyQuestionDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SurveyQuestionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyQuestionDTO record);

    int insertSelective(SurveyQuestionDTO record);

    SurveyQuestionDTO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyQuestionDTO record);

    int updateByPrimaryKey(SurveyQuestionDTO record);
}