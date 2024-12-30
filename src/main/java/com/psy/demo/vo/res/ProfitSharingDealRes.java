package com.psy.demo.vo.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfitSharingDealRes {
    @JsonProperty("order_id")
    private String order_id;
    @JsonProperty("out_order_no")
    private String out_order_no;
    private List<ProfitSharingDealReceiversRes> receivers;
    private String state;
    @JsonProperty("transaction_id")
    private String transaction_id;
}
