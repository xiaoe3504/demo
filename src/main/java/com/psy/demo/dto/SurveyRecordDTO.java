package com.psy.demo.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 用户答题记录表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyRecordDTO {
    /**
    * id
    */
    private Long id;

    /**
    * 用户微信编码
    */
    private Long openId;

    /**
    * 测评id
    */
    private Long surveyId;

    /**
    * 答案分数
    */
    private Long score;

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