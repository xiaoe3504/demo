package com.psy.demo.vo.req;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignReq {
    private String appId;
    private String timestamp;
    private String nonceStr;
    private String prepayIdStr;
}
