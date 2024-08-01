package com.psy.demo.vo.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PayRes {
    private String prepayId;
    private String amount;
    private String tradeNo;
}
