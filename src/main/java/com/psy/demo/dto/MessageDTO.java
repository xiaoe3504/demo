package com.psy.demo.dto;

import java.time.LocalDateTime;

import lombok.*;

/**
    * 聊天详情表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MessageDTO {
    /**
    * id
    */
    private Long id;

    /**
    * 谈话唯一id
    */
    private Integer conversationId;

    /**
    * 发消息的是咨询师0否1是
    */
    private Boolean isSenderPsychologist;

    /**
    * 发的消息详情
    */
    private String message;

    /**
    * 创建时间
    */
    private LocalDateTime createTime;

    /**
    * 更新时间
    */
    private LocalDateTime updateTime;
}