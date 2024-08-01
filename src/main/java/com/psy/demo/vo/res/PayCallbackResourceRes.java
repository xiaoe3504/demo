package com.psy.demo.vo.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PayCallbackResourceRes {
    @JsonProperty("original_type")
    private String original_type;
    @JsonProperty("algorithm")
    private String algorithm;
    @JsonProperty("ciphertext")
    private String ciphertext;
    @JsonProperty("associated_data")
    private String associated_data;
    @JsonProperty("nonce")
    private String nonce;
}
