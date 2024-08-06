package com.psy.demo.vo.res.baichuan;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaiChuanChatRes {
    private String id;
    private String object;
    private long created;
    private String model;
    private List<Choices> choices;
    private Usage usage;
}
