package com.psy.demo.dto;

import java.time.LocalDateTime;

import com.psy.demo.vo.res.survey.SurveyVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 测评表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyDTO {
    /**
    * id
    */
    private Long id;

    /**
    * 测评类型
    */
    private String surveyType;

    /**
    * 测评类型名称
    */
    private String surveyTypeName;

    /**
    * 测评名称
    */
    private String surveyName;

    /**
    * 测评描述
    */
    private String surveyDesc;

    /**
    * 测评描述
    */
    private String surveyRemark;

    /**
    * 背景图片url
    */
    private String src;

    /**
    * 创建时间
    */
    private LocalDateTime createTime;

    /**
    * 更新时间
    */
    private LocalDateTime updateTime;


}