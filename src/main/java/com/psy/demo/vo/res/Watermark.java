package com.psy.demo.vo.res;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Watermark {
    private long timestamp;
    private String appid;
}
