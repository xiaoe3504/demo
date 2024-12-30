package com.psy.demo.vo.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QueryTransactionIdResPayer {
    @JsonProperty("openid")
    private String openid;
}
