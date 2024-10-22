package com.psy.demo.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum MeditationMusicTypeEnum {
    NATURE(1, "大自然"),
    BODY_BREATH(2, "身体与呼吸"),
    WORK(3, "工作职场"),
    LIFE(4, "日常生活"),
    ;
    private int code;
    private String desc;

    MeditationMusicTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getDescByCode(int code) {
        return Arrays.stream(MeditationMusicTypeEnum.values()).
                filter(e -> e.getCode() == code).findFirst()
                .orElseGet(() -> NATURE).getDesc();
    }
}
