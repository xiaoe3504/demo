package com.psy.demo.vo.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CozeBootRes {
    private String id;
    @JsonProperty("conversation_id")
    private String conversation_id;
    @JsonProperty("bot_id")
    private String bot_id;
    private String role;
    private String type;
    private String content;
    @JsonProperty("content_type")
    private String content_type;
    @JsonProperty("chat_id")
    private String chat_id;
    @JsonProperty("section_id")
    private String section_id;
    @JsonProperty("created_at")
    private long created_at;
    @JsonProperty("updated_at")
    private long updated_at;

}
