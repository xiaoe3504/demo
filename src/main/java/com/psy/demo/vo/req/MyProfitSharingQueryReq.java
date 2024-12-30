package com.psy.demo.vo.req;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyProfitSharingQueryReq {
    private String outOrderNo;
    private String transactionId;
}
