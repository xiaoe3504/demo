package com.psy.demo.service.impl;

import com.psy.demo.dto.SurveyDTO;
import com.psy.demo.dto.SurveyDetailDTO;
import com.psy.demo.mapper.SurveyMapper;
import com.psy.demo.mapper.SurveyQuestionMapper;
import com.psy.demo.service.SurveyService;
import com.psy.demo.utils.PojoUtils;
import com.psy.demo.vo.res.survey.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class SurveyServiceImpl implements SurveyService {
    @Autowired
    SurveyMapper surveyMapper;

    @Autowired
    SurveyQuestionMapper questionMapper;


    @Override
    public List<SurveyTypeVO> queryAll() {
        return surveyMapper.selectAll();
    }

    @Override
    public SurveyVO querySurvey(Long surveyId) {
        SurveyDTO dto = surveyMapper.selectByPrimaryKey(surveyId);
        return PojoUtils.genVO(dto);
    }

    @Override
    public SurveyDetailVO querySurveyDetail(Long surveyId) {
        List<SurveyDetailDTO> list = surveyMapper.querySurveyDetail(surveyId);
        SurveyDetailVO res = new SurveyDetailVO();
        Optional<SurveyDetailDTO> first = list.stream().findFirst();
        if (CollectionUtils.isEmpty(list) || !first.isPresent()) {
            return res;
        }
        res.setSurveyId(first.get().getSurveyId());
        res.setSurveyName(first.get().getSurveyName());

        List<SurveyQuestionVO> questions = list.stream().collect(Collectors.groupingBy(SurveyDetailDTO::getQuestionId))
                .entrySet().stream().map(e -> {
                    SurveyQuestionVO questionVO = new SurveyQuestionVO();
                    questionVO.setQuestionId(e.getKey());
                    List<SurveyDetailDTO> listInner = e.getValue();
                    Optional<SurveyDetailDTO> firstInner = listInner.stream().findFirst();
                    if (CollectionUtils.isEmpty(listInner) || !firstInner.isPresent()) {
                        return questionVO;
                    }
                    questionVO.setQuestionText(firstInner.get().getQuestionText());
                    questionVO.setIsMultipleChoice(firstInner.get().getIsMultipleChoice());
                    questionVO.setIsRequired(firstInner.get().getIsRequired());

                    List<SurveyAnswerVO> answers = listInner.stream().map(ee -> {
                        SurveyAnswerVO answerVO = new SurveyAnswerVO();
                        answerVO.setAnswerId(ee.getAnswerId());
                        answerVO.setAnswerText(ee.getAnswerText());
                        answerVO.setRankId(ee.getRankId());
                        answerVO.setScore(ee.getScore());
                        return answerVO;
                    }).collect(Collectors.toList());
                    questionVO.setAnswers(answers);
                    return questionVO;
                }).collect(Collectors.toList());
        res.setQuestions(questions);
        return res;
    }
}
