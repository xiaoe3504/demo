package com.psy.demo.vo.res;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegionVO {
    private String name;
    private String code;
    private List<RegionCity> city;
}
