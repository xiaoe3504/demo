package com.psy.demo.vo.req.baichuan;

import com.psy.demo.vo.res.baichuan.Message;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaiChuanChatReq {
    private String model;
    private CharacterProfile character_profile;
    private List<Message> messages;
    private boolean stream;
    private double temperature;
    private int top_k;
    private int max_tokens;
}
