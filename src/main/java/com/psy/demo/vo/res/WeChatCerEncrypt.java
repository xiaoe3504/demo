package com.psy.demo.vo.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeChatCerEncrypt {
    @JsonProperty("algorithm")
    private String algorithm;
    @JsonProperty("associated_data")
    private String associated_data;
    @JsonProperty("ciphertext")
    private String ciphertext;
    @JsonProperty("nonce")
    private String nonce;

}
