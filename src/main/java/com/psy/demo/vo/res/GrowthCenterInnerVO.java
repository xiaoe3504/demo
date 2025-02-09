package com.psy.demo.vo.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

/**
    * 成长中心表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class GrowthCenterInnerVO {
    /**
     * id
     */
    private String id;

    /**
     * id
     */
    private Long idReal;

    /**
    * 名称
    */
    private String name;

    /**
    * 时长
    */
    private String duration;

    /**
     * 是否播放
     */
    @JsonProperty("isPlay")
    private boolean isPlay;
    /**
     * 音频地址
     */
    private String src;

}