package com.psy.demo.vo.res;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MoodMapResFinalVO {

    /**
    * 心情(1快乐2惊讶3愤怒4悲伤5恐惧)
    */
    private int color;

    /**
    * 用户心情日期
    */
    private int date;

    /**
     * 是否这个月
     */
    private boolean thisMonth;


}