package com.psy.demo.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 分账信息
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfitSharingInfoDTO {
    /**
    * id
    */
    private Long id;

    /**
    * 商户订单号
    */
    private String tradeNo;

    /**
    * 微信订单号
    */
    private String transactionId;

    /**
    * 商户分账单号
    */
    private String outOrderNo;

    /**
    * 微信分账单号
    */
    private String orderId;

    /**
    * 分账钱数
    */
    private String amount;

    /**
    * 创建时间
    */
    private LocalDateTime createTime;

    /**
    * 更新时间
    */
    private LocalDateTime updateTime;
}