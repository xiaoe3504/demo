package com.psy.demo.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum CozeIdEnum {
    COZE_BOOT_ID_FEMALE( "AI心小芽(女)","7457883281737531401"),
    COZE_BOOT_ID_MALE("AI心小芽(男)","7457857320757395493"),
    COZE_BOOT_ID_SHUDONG( "心芽树洞","7457811112122368010"),
    COZE_BOOT_ID_XUSHI( "AI积极叙事专家","7457894630127222799"),
    COZE_BOOT_ID_ZHIDAO( "心芽职道","7458460972060344330"),
    ;
    private String name;
    private String bootId;

    CozeIdEnum( String name,String bootId) {
        this.name = name;
        this.bootId = bootId;
    }

    public static boolean isCozeBootId(String bootId) {
        return Arrays.stream(CozeIdEnum.values()).anyMatch(e -> e.getBootId().equals(bootId));
    }


}
