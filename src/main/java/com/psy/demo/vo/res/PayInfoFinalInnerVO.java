package com.psy.demo.vo.res;


import lombok.*;
import lombok.extern.slf4j.Slf4j;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Slf4j
@Builder
public class PayInfoFinalInnerVO {
    /**
     * 名称
     */
    private String name;
    /**
     * 详情
     */
    private String detail;

}


