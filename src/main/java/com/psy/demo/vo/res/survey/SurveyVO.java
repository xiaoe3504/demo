package com.psy.demo.vo.res.survey;

import lombok.*;

/**
    * 成长中心表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SurveyVO {
    private Long id;
    private String surveyType;
    private String surveyTypeName;
    private String surveyName;
    private String surveyDesc;
    private String surveyRemark;
}