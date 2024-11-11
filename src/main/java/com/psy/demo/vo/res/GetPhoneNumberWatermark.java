package com.psy.demo.vo.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetPhoneNumberWatermark {
    private long timestamp;
    @JsonProperty("appid")
    private String appid;
}
