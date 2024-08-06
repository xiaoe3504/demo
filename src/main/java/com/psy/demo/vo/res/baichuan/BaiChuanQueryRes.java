package com.psy.demo.vo.res.baichuan;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaiChuanQueryRes {
    private String object;
    private List<BaiChuanQueryListRes> data;
    private int total;
    private int page_no;
    private int page_size;
    private int total_pages;
}
