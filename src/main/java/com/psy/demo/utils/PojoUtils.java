package com.psy.demo.utils;

import com.psy.demo.dto.SurveyDTO;
import com.psy.demo.vo.res.survey.SurveyVO;

public class PojoUtils {

    public static SurveyVO genVO(SurveyDTO dto){
        return SurveyVO.builder()
                .id(dto.getId())
                .surveyType(dto.getSurveyType())
                .surveyTypeName(dto.getSurveyTypeName())
                .surveyName(dto.getSurveyName())
                .surveyDesc(dto.getSurveyDesc())
                .surveyRemark(dto.getSurveyRemark())
                .build();
    }
}
