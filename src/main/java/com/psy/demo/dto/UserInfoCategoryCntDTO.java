package com.psy.demo.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 用户使用次数表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoCategoryCntDTO {
    /**
    * id
    */
    private Long id;

    /**
    * openId
    */
    private String openId;

    /**
    * 类型
    */
    private String category;

    /**
    * 类型唯一id
    */
    private int categoryId;

    /**
    * 次数
    */
    private String cnt;

    /**
    * 创建时间
    */
    private LocalDateTime createTime;

    /**
    * 更新时间
    */
    private LocalDateTime updateTime;
}