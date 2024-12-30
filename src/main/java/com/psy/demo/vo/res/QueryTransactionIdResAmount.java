package com.psy.demo.vo.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QueryTransactionIdResAmount {
    private String currency;
    @JsonProperty("payer_currency")
    private String payer_currency;
    @JsonProperty("payer_total")
    private int payer_total;
    private int total;
}
