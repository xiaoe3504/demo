package com.psy.demo.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 三栏笔记
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note3DTO {
    /**
    * id
    */
    private Long id;

    /**
    * 用户open_id唯一的
    */
    private String openId;

    /**
    * 自动化负性观念
    */
    private String mood;

    /**
    * 不合理认知分析类型
    */
    private String analysisType;

    /**
    * 不合理认知分析类型名
    */
    private String analysis;

    /**
    * 不合理认知分析详情
    */
    private String analysisDetail;

    /**
    * 我的理性回应
    */
    private String defend;

    /**
    * 创建时间
    */
    private LocalDateTime createTime;

    /**
    * 更新时间
    */
    private LocalDateTime updateTime;
}