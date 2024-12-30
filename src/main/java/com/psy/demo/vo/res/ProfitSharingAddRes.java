package com.psy.demo.vo.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfitSharingAddRes {
    private String account;
    @JsonProperty("relation_type")
    private String relation_type;
    private String type;
}
