package com.psy.demo.vo.res;

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
    private boolean isMember;

    /**
     * 个人状态0喊他上线1服务中2找他聊
     */
    private String status;

    public static PsychologistVO genPsychologistVO(PsychologistDTO psychologistDTO) {
        if (psychologistDTO == null) {
            throw new BaseException("genPsychologistVO error dto is null...");
        }
        return PsychologistVO.builder()
                .id(psychologistDTO.getId())
                .openId(psychologistDTO.getOpenId())
                .name(psychologistDTO.getName())
                .gender(psychologistDTO.getGender() == 0 ? "female" : "male")
                .slogan(psychologistDTO.getSlogan())
                .backgroundUrl(psychologistDTO.getBackgroundUrl())
                .avatarUrl(psychologistDTO.getAvatarUrl())
                .experienceCnt(psychologistDTO.getExperienceCnt() > 100 ? "100+" : String.valueOf(psychologistDTO.getExperienceCnt()))
                .responseRate(doubleToPercentString(psychologistDTO.getResponseRate()))
                .applauseRate(doubleToPercentString(psychologistDTO.getApplauseRate()))
                .recommendedRate(doubleToPercentString(psychologistDTO.getRecommendedRate()))
                .personIntroduce(psychologistDTO.getPersonIntroduce())
                .listenStyle(StringUtil.getStringArr(psychologistDTO.getListenStyle(), MyConstantString.LISTEN_STYLE_LIST))
                .professionalQualification(psychologistDTO.getProfessionalQualification())
                .expertAreas(StringUtil.getStringArr(psychologistDTO.getPersonIntroduce(), MyConstantString.EXPERT_AREAS_LIST))
                .isMember(psychologistDTO.getIsMember() == 1)
                .status(PsyStatusEnum.getDescByCode(psychologistDTO.getStatus()))
                .build();

    }
}