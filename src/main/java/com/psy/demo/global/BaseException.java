package com.psy.demo.global;

import com.psy.demo.enums.BaseErrorEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Setter
@Getter

public class BaseException extends RuntimeException {
    /**
     * 默认错误码
     */
    private static final int DEFAULT_ERROR_CODE = -1;

    private int errorCode;

    private String errorMsg;

    public BaseException(String errorMsg) {
        super(errorMsg);
        this.errorCode = DEFAULT_ERROR_CODE;
        this.errorMsg = errorMsg;
    }

    public BaseException(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BaseException(BaseErrorEnum baseErrorEnum) {
        this.errorCode = baseErrorEnum.getErrorCode();
        this.errorMsg = baseErrorEnum.getErrorMsg();
    }

}
