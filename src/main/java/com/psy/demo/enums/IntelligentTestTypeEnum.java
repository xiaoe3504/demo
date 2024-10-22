package com.psy.demo.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public enum IntelligentTestTypeEnum {
    MOOD(1, "情绪情感"),
    RELATIONSHIP(2, "人际关系"),
    PERSONALITY(3, "人格特质"),
    THINKING(4, "认知思维"),
    ;
    private int code;
    private String desc;

    IntelligentTestTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getDescByCode(int code) {
        return Arrays.stream(IntelligentTestTypeEnum.values()).
                filter(e -> e.getCode() == code).findFirst()
                .orElseGet(() -> MOOD).getDesc();
    }

    public static Map<String, Integer> genMapUseKeySort() {
        LinkedHashMap<String, Integer> mapUseKeySort = Arrays.stream(IntelligentTestTypeEnum.values())
                .collect(Collectors.toMap(IntelligentTestTypeEnum::getDesc,
                        IntelligentTestTypeEnum::getCode, (e1, e2) -> e1, LinkedHashMap::new));
        mapUseKeySort.entrySet().stream().sorted(Map.Entry.comparingByValue());
        return mapUseKeySort;
    }


}
