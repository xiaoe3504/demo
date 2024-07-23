package com.psy.demo.vo.res;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private String avatarUrl;
    private String city;
    private String country;
    private int gender;
    private String language;
    private String nickName;
    private String province;
}
