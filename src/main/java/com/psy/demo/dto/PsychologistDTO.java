package com.psy.demo.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 心理咨询师表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PsychologistDTO {
    /**
    * id
    */
    private Long id;

    /**
    * 用户open_id唯一的
    */
    private String openId;

    /**
    * 用户名
    */
    private String name;

    /**
    * 性别(0女1男)
    */
    private Integer gender;

    /**
    * 口号
    */
    private String slogan;

    /**
    * 背景图
    */
    private String backgroundUrl;

    /**
    * 用户头像图标
    */
    private String avatarUrl;

    /**
    * 经验人数
    */
    private Integer experienceCnt;

    /**
    * 响应率
    */
    private Double responseRate;

    /**
    * 好评率
    */
    private Double applauseRate;

    /**
    * 推荐率
    */
    private Double recommendedRate;

    /**
    * 个人介绍
    */
    private String personIntroduce;

    /**
    * 倾听风格
    */
    private String listenStyle;

    /**
    * 专业资质
    */
    private String professionalQualification;

    /**
    * 擅长领域
    */
    private String expertAreas;

    /**
    * 是否是严选0否1是
    */
    private Integer isMember;

    /**
    * 个人状态0喊他上线1服务中2找他聊
    */
    private Integer status;

    /**
    * 创建时间
    */
    private LocalDateTime createTime;

    /**
    * 更新时间
    */
    private LocalDateTime updateTime;
}