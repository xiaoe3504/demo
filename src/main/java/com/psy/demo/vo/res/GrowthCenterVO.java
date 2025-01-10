package com.psy.demo.vo.res;

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
public class GrowthCenterVO {
    /**
    * id
    */
    private String introduce;

    /**
    * 名称
    */
    private List<GrowthCenterInnerVO> list;


}