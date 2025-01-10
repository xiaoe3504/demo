package com.psy.demo.vo.req;

import lombok.*;

import java.time.LocalDateTime;

/**
    * 用户信息
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserInfoVO {
    /**
    * openId
    */
    private String openId;

    /**
    * 电话号码
    */
    private String phoneNumber;

    /**
     * 机构id
     */
    private Long organizationId;

    /**
     * 机构id
     */
    private String organizationName;
    /**
    * 昵称
    */
    private String nickName;

    /**
     * 真实姓名
     */
    private String realName;

    /**
    * 密码
    */
    private String password;

    /**
    * 头像url
    */
    private String avatarUrl;

}