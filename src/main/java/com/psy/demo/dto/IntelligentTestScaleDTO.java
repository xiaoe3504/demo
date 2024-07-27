package com.psy.demo.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 智能测评量表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IntelligentTestScaleDTO {
    /**
    * id
    */
    private Long id;

    /**
    * 类别
    */
    private String type;

    /**
    * 名称
    */
    private String name;

    /**
    * 标语
    */
    private String title;

    /**
    * 是否收费
    */
    private String isFree;

    /**
    * 图片url
    */
    private String avatarUrl;

    /**
     * 查看人数
     */
    private Long cnt;

    /**
    * 创建时间
    */
    private LocalDateTime createTime;

    /**
    * 更新时间
    */
    private LocalDateTime updateTime;
}