package com.psy.demo.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 答案表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyAnswerDTO {
    /**
    * id
    */
    private Long id;

    /**
    * 问题id
    */
    private Long questionId;

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

    /**
    * 创建时间
    */
    private LocalDateTime createTime;

    /**
    * 更新时间
    */
    private LocalDateTime updateTime;
}