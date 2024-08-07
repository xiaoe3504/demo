package com.psy.demo.vo.res;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoRes {
    private String sessionKey;
    private String encryptedData;
    private String iv;
    private UserInfoVO userInfoVO;
    private String signature;
}
