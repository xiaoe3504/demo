package com.psy.demo.vo.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
    * 机构表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationVO {
    /**
    * id
    */
    private String id;

    /**
    * 机构名称
    */
    private String name;

}