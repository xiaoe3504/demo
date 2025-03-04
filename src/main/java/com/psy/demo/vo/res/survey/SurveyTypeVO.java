package com.psy.demo.vo.res.survey;

import lombok.*;

import java.util.List;

/**
    * 成长中心表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SurveyTypeVO {
    private String surveyType;
    private String surveyTypeName;
    private int count;
}