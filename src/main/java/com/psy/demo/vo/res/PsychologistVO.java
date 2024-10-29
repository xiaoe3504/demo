package com.psy.demo.vo.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.psy.demo.dto.PsychologistDTO;
import com.psy.demo.enums.PsyStatusEnum;
import com.psy.demo.global.BaseException;
import com.psy.demo.utils.MyConstantString;
import com.psy.demo.utils.StringUtil;
import lombok.*;

import static com.psy.demo.utils.StringUtil.doubleToPercentString;

/**
 * 心理咨询师表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PsychologistVO {
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
     * 用户头像图标
     */
    private String avatarUrl;
    /**
     * 个人状态0喊他上线1服务中2找他聊
     */
    private Integer status;
    /**
     * 性别(0女1男)
     */
    private String gender;
    /**
     * 口号
     */
    private String slogan;
    /**
     * 咨询师语录
     */
    private String audio;
    /**
     * 谈话数量
     */
    private String experienceCnt;
    /**
     * 好评率
     */
    private String applauseRate;
    /**
     * 擅长领域
     */
    private String[] expertAreas;
    /**
     * 价格
     */
    private Double price;
    /**
     * 是否是严选0否1是
     */
    @JsonProperty("isMember")
    private boolean isMember;


    /**
     * 背景图
     */
    private String backgroundUrl;

    /**
     * 响应率
     */
    private String responseRate;

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


}