package com.psy.demo.vo.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenRes {
    @JsonProperty("access_token")
    private String access_token;
    @JsonProperty("expires_in")
    private String expires_in;
}
