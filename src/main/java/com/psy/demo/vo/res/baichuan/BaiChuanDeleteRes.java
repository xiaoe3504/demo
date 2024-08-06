package com.psy.demo.vo.res.baichuan;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaiChuanDeleteRes {
    private int id;
    private String object;
    private boolean deleted;
}
