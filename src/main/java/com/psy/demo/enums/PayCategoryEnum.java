package com.psy.demo.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum PayCategoryEnum {
    TYPE_TEST(0, "TYPE_TEST", "心芽测评"),
    TYPE_MEDITATION(1, "TYPE_MEDITATION", "正念冥想"),
    TYPE_MEMBER(2, "TYPE_MEMBER", "PRO会员订阅"),
    TYPE_PSYCHOLOGIST(2, "TYPE_PSYCHOLOGIST", "人工疗愈"),
    TYPE_GROWTH_CENTER(2, "TYPE_GROWTH_CENTER", "成长中心"),
    ;
    private int code;
    private String name;
    private String desc;

    PayCategoryEnum(int code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public static boolean isCategoryType(String s) {
        return Arrays.stream(PayCategoryEnum.values()).anyMatch(e -> e.getName().equals(s));
    }

    public static String getDescByName(String name) {
        return Arrays.stream(PayCategoryEnum.values()).filter(e -> e.getName().equals(name)).findFirst().orElseGet(() -> TYPE_TEST).getDesc();
    }

}
