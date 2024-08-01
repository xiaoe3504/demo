package com.psy.demo.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 冥想音乐
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeditationMusicDTO {
    /**
    * id
    */
    private Long id;

    /**
    * 类别
    */
    private String type;

    /**
    * 名称
    */
    private String name;

    /**
    * 标语
    */
    private String title;

    /**
    * 是否收费
    */
    private String isFree;

    /**
    * 时长
    */
    private Integer duration;

    /**
    * 图片url
    */
    private String avatarUrl;

    /**
    * mp3名称
    */
    private String mp3Name;

    /**
    * 创建时间
    */
    private LocalDateTime createTime;

    /**
    * 更新时间
    */
    private LocalDateTime updateTime;
    /**
     * 是否支付
     */
    @JsonProperty("isPayed")
    private boolean isPayed;
}