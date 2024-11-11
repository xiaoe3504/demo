package com.psy.demo.vo.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetPhoneNumberRes {
    private int errcode;
    private String errmsg;
    @JsonProperty("phone_info")
    private GetPhoneNumberPhoneInfo phone_info;

}
