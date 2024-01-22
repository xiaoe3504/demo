package com.psy.demo.vo.res;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRes {
    private String openid;
    private String session_key;
    private String unionid;
    private int errcode;
    private String errmsg;
}
