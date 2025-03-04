package com.psy.demo.vo.req.survey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 答案表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyRecordVO {
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