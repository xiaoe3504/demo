package com.psy.demo.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 木鱼表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MuyuDTO {
    /**
    * id
    */
    private Long id;

    /**
    * 用户唯一open_id
    */
    private String openId;

    /**
    * 敲击日
    */
    private String hitDate;

    /**
    * 敲击数
    */
    private Integer hitCnt;

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