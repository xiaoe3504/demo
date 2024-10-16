package com.psy.demo.vo.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MoodMapResFinalFinalVO {

    /**
     * 每日心情
     */
    MoodMapResFinalVO[][] moods;
    /**
     * 当日心情
     */
    String moodToday;



}