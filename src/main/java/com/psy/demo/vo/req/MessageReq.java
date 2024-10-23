package com.psy.demo.vo.req;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageReq {
    private String psychologistId;
    private String clientId;
}
