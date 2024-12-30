package com.psy.demo.vo.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QueryTransactionIdRes {
    private QueryTransactionIdResAmount amount;
    @JsonProperty("appid")
    private String appid;
    private String attach;
    @JsonProperty("bank_type")
    private String bank_type;
    private String mchid;
    @JsonProperty("out_trade_no")
    private String out_trade_no;
    private QueryTransactionIdResPayer payer;
    @JsonProperty("promotion_detail")
    private List<String> promotion_detail;
    @JsonProperty("success_time")
    private Date success_time;
    @JsonProperty("trade_state")
    private String trade_state;
    @JsonProperty("trade_state_desc")
    private String trade_state_desc;
    @JsonProperty("trade_type")
    private String trade_type;
    @JsonProperty("transaction_id")
    private String transaction_id;
}
