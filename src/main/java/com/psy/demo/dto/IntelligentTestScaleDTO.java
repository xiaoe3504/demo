package com.psy.demo.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
    * 智能测评量表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    @JsonProperty("isFree")
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