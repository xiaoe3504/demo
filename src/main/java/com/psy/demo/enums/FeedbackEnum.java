package com.psy.demo.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum FeedbackEnum {
    GOOD(0, "好评"),
    MID(1, "中评"),
    BAD(2, "差评"),
    ;
    private int code;
    private String desc;

    FeedbackEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static boolean isFeedBackType(String desc) {
        return Arrays.stream(FeedbackEnum.values()).anyMatch(e -> e.getDesc().equals(desc));
    }

}
