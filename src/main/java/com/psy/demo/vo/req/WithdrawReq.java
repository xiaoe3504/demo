package com.psy.demo.vo.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WithdrawReq {
    @JsonProperty("appid")
    private String appid;
    @JsonProperty("out_batch_no")
    private String out_batch_no;
    @JsonProperty("batch_name")
    private String batch_name;
    @JsonProperty("batch_remark")
    private String batch_remark;
    @JsonProperty("total_amount")
    private long total_amount;
    @JsonProperty("total_num")
    private int total_num;
    @JsonProperty("transfer_detail_list")
    private List<TransferDetailList> transfer_detail_list;
}
