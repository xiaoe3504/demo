package com.psy.demo.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 成长中心表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GrowthCenterDTO {
    /**
    * id
    */
    private Long id;

    /**
    * 类型
    */
    private String type;

    private String introduce;

    /**
    * 名称
    */
    private String name;

    /**
    * 时长
    */
    private String duration;

    /**
    * 是否删除
    */
    private Integer isDel;

    /**
    * 创建时间
    */
    private LocalDateTime createTime;

    /**
    * 更新时间
    */
    private LocalDateTime updateTime;
}