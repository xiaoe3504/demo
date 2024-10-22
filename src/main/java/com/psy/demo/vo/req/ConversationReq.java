package com.psy.demo.vo.req;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConversationReq {
    private String psychologistId;
    private String clientId;
    private String  message;
    @JsonProperty("isSenderPsychologist")
    private Boolean isSenderPsychologist;
}
