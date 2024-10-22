package com.psy.demo.dto;

import java.time.LocalDateTime;

import lombok.*;

/**
    * 聊天会话表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ConversationDTO {
    /**
    * id
    */
    private Integer id;

    /**
    * 咨询师open_id
    */
    private String psychologistId;

    /**
    * 来访者open_id
    */
    private String clientId;

    /**
    * 创建时间
    */
    private LocalDateTime createTime;

    /**
    * 更新时间
    */
    private LocalDateTime updateTime;
}