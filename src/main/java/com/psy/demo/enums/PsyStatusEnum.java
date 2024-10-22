package com.psy.demo.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum PsyStatusEnum {
    TYPE_OFFLINE(0, "喊他上线"),
    TYPE_ON_SERVICE(1, "服务中"),
    TYPE_ONLINE(2, "找他聊"),
    ;
    private int code;
    private String desc;

    PsyStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getDescByCode(int code) {
        return Arrays.stream(PsyStatusEnum.values()).
                filter(e -> e.getCode() == code).findFirst()
                .orElseGet(() -> TYPE_OFFLINE).getDesc();
    }
}
