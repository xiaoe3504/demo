package com.psy.demo.vo.req;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccessTokenReq {
    @JsonProperty("grant_type")
    private String grant_type;
    @JsonProperty("appid")
    private String appid;
    @JsonProperty("secret")
    private String secret;

}
