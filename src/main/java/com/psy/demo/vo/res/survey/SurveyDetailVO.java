package com.psy.demo.vo.res.survey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 问题表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyDetailVO {
    /**
     * 测评id
     */
    private Long surveyId;
    /**
     * 测评名称
     */
    private String surveyName;

    /**
     * 测评问题
     */
    private List<SurveyQuestionVO> questions;
}