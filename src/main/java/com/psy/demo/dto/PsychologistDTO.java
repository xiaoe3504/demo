package com.psy.demo.dto;

import java.time.LocalDateTime;

import com.psy.demo.enums.PsyStatusEnum;
import com.psy.demo.global.BaseException;
import com.psy.demo.utils.MyConstantString;
import com.psy.demo.utils.StringUtil;
import com.psy.demo.vo.res.PsychologistVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.psy.demo.utils.StringUtil.doubleToPercentString;

/**
 * 心理咨询师表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
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
     * 性别(0女1男)
     */
    private Integer experienceYear;


    /**
     * 口号
     */
    private String slogan;

    /**
     * 咨询师语录
     */
    private String audio;

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
     * 价格
     */
    private Double price;

    /**
     * 是否是严选0否1是
     */
    private Integer isMember;

    /**
     * 个人状态0喊他上线1服务中2找他聊
     */
    private Integer isDel;

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


    public static PsychologistVO genPsychologistVOByDTO(PsychologistDTO psychologistDTO) {
        if (psychologistDTO == null) {
            throw new BaseException("genPsychologistVO error dto is null...");
        }
        PsychologistVO res;
        try {
            res = getBuild(psychologistDTO);
        } catch (Exception e) {
            log.error("genPsychologistVOByDTO error..." + e.getMessage(), e);
            throw new BaseException("genPsychologistVOByDTO error");
        }
        return res;

    }

    private static PsychologistVO getBuild(PsychologistDTO dto) {
        return PsychologistVO.builder()
                .id(dto.getId())
                .openId(dto.getOpenId())
                .name(dto.getName())
                .avatarUrl(dto.getAvatarUrl())
                .status(dto.getStatus())
                .gender(dto.getGender() == 0 ? "female" : "male")
                .experienceYear(dto.getExperienceYear())
                .slogan(dto.getSlogan())
                .audio(dto.getAudio())
                .experienceCnt(getExperienceCntStr(dto.getExperienceCnt()))
                .applauseRate(doubleToPercentString(dto.getApplauseRate()))
                .expertAreas(dto.getExpertAreas().split(","))
                .price(dto.getPrice())
                .isMember(dto.getIsMember() == 1)
                .backgroundUrl(dto.getBackgroundUrl())
                .responseRate(doubleToPercentString(dto.getResponseRate()))
                .recommendedRate(doubleToPercentString(dto.getRecommendedRate()))
                .personIntroduce(dto.getPersonIntroduce())
                .listenStyle(dto.getListenStyle().split(","))
                .professionalQualification(dto.getProfessionalQualification())
                .build();
    }

    private static String getExperienceCntStr(Integer experienceCnt) {
        if (experienceCnt > 200) {
            return "200+";
        } else if (experienceCnt > 150) {
            return "150+";
        } else if (experienceCnt > 100) {
            return "100+";
        } else if (experienceCnt > 50) {
            return "50+";
        } else {
            return String.valueOf(experienceCnt);
        }
    }
}