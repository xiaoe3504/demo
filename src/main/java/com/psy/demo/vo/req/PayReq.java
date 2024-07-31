package com.psy.demo.vo.req;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PayReq {
    private String openId;
    private int amount;
    private String description;
    private String attach;
}
