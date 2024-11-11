package com.psy.demo.vo.req;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetPhoneNumberReq {
    @JsonProperty("code")
    private String code;
}
