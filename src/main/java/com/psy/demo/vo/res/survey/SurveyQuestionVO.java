package com.psy.demo.vo.res.survey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 问题表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyQuestionVO {
    /**
     * 测评id
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


    private List<SurveyAnswerVO> answers;
}