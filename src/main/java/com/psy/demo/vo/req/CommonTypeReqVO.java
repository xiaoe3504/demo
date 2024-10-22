package com.psy.demo.vo.req;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommonTypeReqVO {
    private String openId;
    private int type;
}
