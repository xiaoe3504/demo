package com.psy.demo.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 问题表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyQuestionDTO {
    /**
    * id
    */
    private Long id;

    /**
    * 测评id
    */
    private Long surveyId;

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
    * 创建时间
    */
    private LocalDateTime createTime;

    /**
    * 更新时间
    */
    private LocalDateTime updateTime;
}