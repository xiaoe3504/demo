package com.psy.demo.vo.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CozeData {
    @JsonProperty("created_at")
    private long created_at;
    private String id;
    @JsonProperty("last_section_id")
    private String last_section_id;
    @JsonProperty("meta_data")
    private CozeMetaData meta_data;
    
}
