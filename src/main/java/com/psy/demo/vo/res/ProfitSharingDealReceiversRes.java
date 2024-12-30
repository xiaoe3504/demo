package com.psy.demo.vo.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfitSharingDealReceiversRes {
    private String account;
    private int amount;
    @JsonProperty("create_time")
    private Date create_time;
    private String description;
    @JsonProperty("detail_id")
    private String detail_id;
    @JsonProperty("finish_time")
    private Date finish_time;
    private String result;
    private String type;
}
