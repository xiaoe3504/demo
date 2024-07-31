package com.psy.demo.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum PayCategoryEnum {
    TYPE_TEST(0, "TYPE_TEST"),
    TYPE_MEDITATION(1, "TYPE_MEDITATION"),
    ;
    private int typeCode;
    private String typeName;

    PayCategoryEnum(int typeCode, String typeName) {
        this.typeCode = typeCode;
        this.typeName = typeName;
    }

    public static boolean isCategoryType(String s){
       return Arrays.stream(PayCategoryEnum.values()).anyMatch(e-> e.getTypeName().equals(s));
    }
}
