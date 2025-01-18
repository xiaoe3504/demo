package com.psy.demo.vo.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CozeAccessTokenRes {
    @JsonProperty("access_token")
    private String access_token;
    @JsonProperty("expires_in")
    private int expires_in;
    @JsonProperty("token_type")
    private String token_type;
}
