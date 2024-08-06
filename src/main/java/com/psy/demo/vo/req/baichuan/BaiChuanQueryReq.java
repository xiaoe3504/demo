package com.psy.demo.vo.req.baichuan;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaiChuanQueryReq {
    private String type;
    private int page_no;
    private int page_size;
}
