package com.psy.demo.vo.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@lombok.Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeChatCerData {
    @JsonProperty("effective_time")
    private Date effective_time;
    @JsonProperty("encrypt_certificate")
    private WeChatCerEncrypt encrypt_certificate;
    @JsonProperty("expire_time")
    private Date expire_time;
    @JsonProperty("serial_no")
    private String serial_no;

}
