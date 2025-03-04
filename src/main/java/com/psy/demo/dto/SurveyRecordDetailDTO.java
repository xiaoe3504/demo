package com.psy.demo.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 用户答题记录详情表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyRecordDetailDTO {
    /**
    * id
    */
    private Long id;

    /**
    * 测评记录id
    */
    private Long recordId;

    /**
    * 问题id
    */
    private Long questionId;

    /**
    * 答案id
    */
    private Long answerId;

    /**
    * 答案内容
    */
    private Long answerText;

    /**
    * 开始时间
    */
    private LocalDateTime startTime;

    /**
    * 结束时间
    */
    private LocalDateTime endTime;

    /**
    * 创建时间
    */
    private LocalDateTime createTime;

    /**
    * 更新时间
    */
    private LocalDateTime updateTime;
}