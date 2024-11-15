package com.psy.demo.vo.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferDetailList {
    @JsonProperty("out_detail_no")
    private String out_detail_no;
    @JsonProperty("transfer_amount")
    private long transfer_amount;
    @JsonProperty("transfer_remark")
    private String transfer_remark;
    @JsonProperty("openid")
    private String openid;
    @JsonProperty("user_name")
    private String user_name;

}
