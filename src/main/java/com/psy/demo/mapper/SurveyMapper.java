package com.psy.demo.mapper;

import com.psy.demo.dto.SurveyDTO;
import com.psy.demo.dto.SurveyDetailDTO;
import com.psy.demo.vo.res.survey.SurveyTypeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SurveyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyDTO record);

    int insertSelective(SurveyDTO record);

    SurveyDTO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyDTO record);

    int updateByPrimaryKey(SurveyDTO record);

    List<SurveyTypeVO> selectAll();

    List<SurveyDetailDTO> querySurveyDetail(@Param("surveyId") Long surveyId);
}