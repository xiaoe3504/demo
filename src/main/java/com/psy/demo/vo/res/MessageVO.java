package com.psy.demo.vo.res;


import lombok.*;

import java.time.LocalDateTime;


/**
 * 三栏笔记
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageVO {
    /**
     * id
     */
    private Long id;

    /**
     * 是否倾听师发的
     */
    private boolean isSenderPsychologist;

    /**
     * 发送方名称
     */
    private String senderId;

    /**
     * 发送方名称
     */
    private String senderName;

    /**
     * 发送方头像
     */
    private String senderAvatarUrl;

    /**
     * 发送内容
     */
    private String message;

    /**
     * 发送时间
     */
    private String time;

}