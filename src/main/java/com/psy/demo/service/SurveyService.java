package com.psy.demo.service;


import com.psy.demo.vo.res.survey.SurveyDetailVO;
import com.psy.demo.vo.res.survey.SurveyTypeVO;
import com.psy.demo.vo.res.survey.SurveyVO;

import java.util.List;

public interface SurveyService {
    List<SurveyTypeVO> queryAll();

    SurveyVO querySurvey(Long surveyId);

    SurveyDetailVO querySurveyDetail(Long surveyId);
}
