package com.psy.demo.vo.res;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MoodMapResVO {

    /**
    * 心情(1快乐2惊讶3愤怒4悲伤5恐惧)
    */
    private int mood;

    /**
    * 用户心情日期
    */
    private String moodDate;


}