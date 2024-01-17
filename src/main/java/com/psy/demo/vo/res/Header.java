package com.psy.demo.vo.res;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Header {
    int code;
    int status;
    String sid;
}