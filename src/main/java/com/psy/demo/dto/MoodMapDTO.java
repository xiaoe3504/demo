package com.psy.demo.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoodMapDTO {
    /**
    * id
    */
    private Long id;

    /**
    * 用户open_id唯一的
    */
    private String openId;

    /**
    * 心情(1快乐2惊讶3愤怒4悲伤5恐惧)
    */
    private int mood;

    /**
    * 用户心情日期
    */
    private String moodDate;

    /**
    * 创建时间
    */
    private LocalDateTime createTime;

    /**
    * 更新时间
    */
    private LocalDateTime updateTime;
}