package com.psy.demo.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public enum GrowthCenterTypeEnum {
    JOB(1, "job","职场减压与发展",8),
    WORRY(2, "worry","焦虑抑郁缓解",6),
    MOOD(3, "mood","情绪自我调节",8),
    RELATION(4,"relation", "亲密关系之旅",7),
    TEENAGER(5, "teenager","青少年学习力",7),
    ;
    private int code;
    private String  type;
    private String desc;
    private int cnt;

    GrowthCenterTypeEnum(int code, String type, String desc,int cnt) {
        this.code = code;
        this.type = type;
        this.desc = desc;
        this.cnt=cnt;
    }
    public static boolean isGrowthCenterType(String type) {
        return Arrays.stream(GrowthCenterTypeEnum.values()).anyMatch(e -> e.getType() .equals(type));
    }

    public static String getDescByCode(int code) {
        return Arrays.stream(GrowthCenterTypeEnum.values()).
                filter(e -> e.getCode() == code).findFirst()
                .orElseGet(() -> MOOD).getDesc();
    }

    public static Map<String, Integer> genMapUseKeySort() {
        LinkedHashMap<String, Integer> mapUseKeySort = Arrays.stream(GrowthCenterTypeEnum.values())
                .collect(Collectors.toMap(GrowthCenterTypeEnum::getDesc,
                        GrowthCenterTypeEnum::getCode, (e1, e2) -> e1, LinkedHashMap::new));
        mapUseKeySort.entrySet().stream().sorted(Map.Entry.comparingByValue());
        return mapUseKeySort;
    }


}
