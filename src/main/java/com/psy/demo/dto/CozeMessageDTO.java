package com.psy.demo.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * coze对话消息表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CozeMessageDTO {
    /**
    * 消息id
    */
    private Long id;


    /**
    * 谈话唯一id
    */
    private String cozeMessageId;

    /**
    * 谈话唯一id
    */
    private String cozeConversationId;

    /**
    * 发消息的是机器人0否1是
    */
    private Integer isSenderBoot;

    /**
    * 发的消息详情
    */
    private String content;

    /**
    * 创建时间
    */
    private LocalDateTime createTime;

    /**
    * 更新时间
    */
    private LocalDateTime updateTime;
}