package com.psy.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
    * 为了放三张表join出来的结果
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyDetailDTO {
    /**
    * id
    */
    private Long surveyId;
    /**
    * 测评名称
    */
    private String surveyName;
    /**
    * 背景图片url
    */
    private String src;
    /**
     * id
     */
    private Long questionId;
    /**
     * 问题内容
     */
    private String questionText;
    /**
     * 是否多选
     */
    private Integer isMultipleChoice;

    /**
     * 是否必填
     */
    private Integer isRequired;
    /**
     * id
     */
    private Long answerId;

    /**
     * 排名位置
     */
    private Long rankId;

    /**
     * 答案分数
     */
    private Long score;

    /**
     * 答案内容
     */
    private String answerText;



}