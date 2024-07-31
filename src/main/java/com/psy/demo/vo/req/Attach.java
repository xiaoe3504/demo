package com.psy.demo.vo.req;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Attach {
    //类型
    private String type;
    //唯一键
    private String uniId;
}
