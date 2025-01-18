package com.psy.demo.vo.res;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CozeConversationAddRes {
    private int code;
    private CozeData data;
    private String msg;
}
