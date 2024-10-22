package com.psy.demo.vo.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * 心理咨询师表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PsychologistAllVO {
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
    private String gender;

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
    private String experienceCnt;

    /**
     * 响应率
     */
    private String responseRate;

    /**
     * 好评率
     */
    private String applauseRate;

    /**
     * 推荐率
     */
    private String recommendedRate;

    /**
     * 个人介绍
     */
    private String personIntroduce;

    /**
     * 倾听风格
     */
    private String[] listenStyle;

    /**
     * 专业资质
     */
    private String professionalQualification;

    /**
     * 擅长领域
     */
    private String[] expertAreas;

    /**
     * 是否是严选0否1是
     */
    @JsonProperty("isMember")
    private boolean isMember;

    /**
     * 个人状态0喊他上线1服务中2找他聊
     */
    private String status;


}