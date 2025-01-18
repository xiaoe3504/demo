package com.psy.demo.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * access_token表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessTokenDTO {
    /**
    * id
    */
    private Long id;

    /**
    * 类型
    */
    private String type;

    /**
    * token
    */
    private String accessToken;

    /**
    * 失效时间
    */
    private Integer expiresIn;

    /**
    * token类型
    */
    private String tokenType;

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