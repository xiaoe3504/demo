package com.psy.demo.enums;

import lombok.Getter;

@Getter
public enum BaseErrorEnum {
    SUC(0, "成功"),
    SYSTEM_ERROR(-1, "系统未知错误"),
    HTTP_ERROR(1001, "http请求错误"),
    PARAMETER_ERROR(1004, "参数错误"),
    ;
    private int errorCode;
    private String errorMsg;

    BaseErrorEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

}
