package com.psy.demo.vo.res.baichuan;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Choices {
    private int index;
    private Message message;
    private String finish_reason;
}
