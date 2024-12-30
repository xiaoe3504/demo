package com.psy.demo.vo.req;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyProfitSharingReq {
    private String tradeNo;
    private String transactionId;
    private String openId;
    private int amount;
}
