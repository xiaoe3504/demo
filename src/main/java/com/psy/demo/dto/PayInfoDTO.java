package com.psy.demo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 支付信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayInfoDTO {
    /**
     * id
     */
    private Long id;

    /**
     * id 对应的名字
     */
    private String name;

    /**
     * 用户open_id唯一的
     */
    private String openId;

    /**
     * 咨询师id
     */
    private String psychologistId;

    /**
     * 大类，TYPE_TEST,TYPE_MEDITATION两种
     */
    private String category;

    /**
     * 大类下的id唯一的
     */
    private String uniId;

    /**
     * 价钱
     */
    private String amount;

    /**
     * 订单号
     */
    private String tradeNo;

    /**
     * 订单描述
     */
    private String description;

    /**
     * 订单评价
     */
    private String feedback;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}