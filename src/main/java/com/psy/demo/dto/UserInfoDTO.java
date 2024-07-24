package com.psy.demo.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 用户信息
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {
    /**
    * id
    */
    private Long id;

    /**
    * openId
    */
    private String openId;

    /**
    * 电话号码
    */
    private String phoneNumber;

    /**
    * 昵称
    */
    private String nickName;

    /**
    * 密码
    */
    private String password;

    /**
    * 头像url
    */
    private String avatarUrl;

    /**
    * 创建时间
    */
    private LocalDateTime createTime;

    /**
    * 更新时间
    */
    private LocalDateTime updateTime;
}