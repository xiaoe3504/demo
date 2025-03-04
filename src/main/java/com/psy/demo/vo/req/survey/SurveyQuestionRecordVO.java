package com.psy.demo.vo.req.survey;

import com.psy.demo.vo.res.survey.SurveyAnswerVO;
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
public class SurveyQuestionRecordVO {
    /**
     * 测评id
     */
    private Long questionId;

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