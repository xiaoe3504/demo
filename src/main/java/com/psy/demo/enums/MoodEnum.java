package com.psy.demo.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Getter
public enum MoodEnum {
    MOOD_DEFAULT(0, "默认","#DDDDDD","\uD83D\uDE10 未选"),
    MOOD_HAPPY(1, "快乐","#52ED71","\uD83D\uDE00 快乐"),
    MOOD_SURPRISED(2, "惊讶","#FFC832","\uD83D\uDE32 惊讶"),
    MOOD_ANGER(3, "愤怒","#F45B31","\uD83D\uDE20 愤怒"),
    MOOD_SAD(4, "悲伤","#A4C9F0","\uD83D\uDE25 悲伤"),
    MOOD_FEAR(5, "恐惧","#BFBFBF","\uD83D\uDE28 恐惧"),
    ;
    private int code;
    private String name;
    private String color;
    private String desc;

    MoodEnum(int code, String name, String color, String desc) {
        this.code = code;
        this.name = name;
        this.color = color;
        this.desc = desc;
    }

    public static boolean isMoodType(int mood) {
        return Arrays.stream(MoodEnum.values()).anyMatch(e -> e.getCode() == mood);
    }
    public static String getColorByCode(int code) {
        return Arrays.stream(MoodEnum.values()).filter(e -> e.getCode() == code).findFirst().orElseGet(()->MOOD_DEFAULT).getColor();
    }

    public static String getDescByCode(int code) {
        return Arrays.stream(MoodEnum.values()).filter(e -> e.getCode() == code).findFirst().orElseGet(()->MOOD_DEFAULT).getDesc();
    }

    public static void main(String[] args) {
        System.out.println(getColorByCode(5));;
    }
}
