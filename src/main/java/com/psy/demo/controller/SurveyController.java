package com.psy.demo.controller;

import com.psy.demo.service.SurveyService;
import com.psy.demo.vo.res.survey.SurveyDetailVO;
import com.psy.demo.vo.res.survey.SurveyTypeVO;
import com.psy.demo.vo.res.survey.SurveyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/survey")
public class SurveyController {
    @Autowired
    SurveyService surveyService;

    @GetMapping("/queryAll")
    public List<SurveyTypeVO> queryAll() {
        return surveyService.queryAll();
    }

    @GetMapping("/querySurvey/{surveyId}")
    public SurveyVO querySurvey(@PathVariable Long surveyId) {
        return surveyService.querySurvey(surveyId);
    }

    @GetMapping("/querySurveyDetail/{surveyId}")
    public SurveyDetailVO querySurveyDetail(@PathVariable Long surveyId) {
        return surveyService.querySurveyDetail(surveyId);
    }

    @GetMapping("/conductSurvey}")
    public SurveyDetailVO conductSurvey(@RequestBody Long surveyId) {
        return surveyService.querySurveyDetail(surveyId);
    }

}
