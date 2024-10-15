package com.psy.demo.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum MoodEnum {
    MOOD_HAPPY(1, "快乐"),
    MOOD_SURPRISED(2, "惊讶"),
    MOOD_ANGER(3, "愤怒"),
    MOOD_SAD(4, "悲伤"),
    MOOD_FEAR(5, "恐惧"),
    ;
    private int code;
    private String name;

    MoodEnum(int typeCode, String typeName) {
        this.code = typeCode;
        this.name = typeName;
    }

    public static boolean isMoodType(int mood) {
        return Arrays.stream(MoodEnum.values()).anyMatch(e -> e.getCode() == mood);
    }
}
