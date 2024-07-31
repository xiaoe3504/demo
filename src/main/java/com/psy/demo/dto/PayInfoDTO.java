package com.psy.demo.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 支付信息
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayInfoDTO {
    /**
    * id
    */
    private Long id;

    /**
    * 用户open_id唯一的
    */
    private String openId;

    /**
    * 大类，TYPE_TEST,TYPE_MEDITATION两种
    */
    private String category;

    /**
    * 大类下的id唯一的
    */
    private String uniId;

    /**
    * 创建时间
    */
    private LocalDateTime createTime;

    /**
    * 更新时间
    */
    private LocalDateTime updateTime;
}