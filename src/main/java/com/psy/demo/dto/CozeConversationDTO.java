package com.psy.demo.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * coze会话表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CozeConversationDTO {
    /**
    * 对话id
    */
    private Long id;

    /**
    * coze谈话唯一id
    */
    private String cozeConversationId;

    /**
    * 用户open_id
    */
    private String openId;

    /**
    * 机器人id
    */
    private String bootId;

    /**
    * 创建时间
    */
    private LocalDateTime createTime;

    /**
    * 更新时间
    */
    private LocalDateTime updateTime;
}