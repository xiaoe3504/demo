package com.psy.demo.vo.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawResVO {
    @JsonProperty("out_batch_no")
    private String out_batch_no;
    @JsonProperty("batch_id")
    private String batch_id;
    @JsonProperty("create_time")
    private Date create_time;
    @JsonProperty("batch_status")
    private String batch_status;
}
