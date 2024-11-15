package com.psy.demo.vo.req;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyWithdrawReq {
    private String openId;
    private int amount;
}
