package com.psy.demo.vo.res;

import com.psy.demo.enums.BaseErrorEnum;
import com.psy.demo.global.BaseException;
import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseRes<T> implements Serializable {
    private int errorCode;
    private String errorMsg;
    private T res;


    public static <T> BaseRes<T> ofSuccess(T t) {
        return new BaseRes<>(BaseErrorEnum.SUC.getErrorCode(),
                BaseErrorEnum.SUC.getErrorMsg(), t);
    }

    public static <T> BaseRes<T> ofFail() {
        return new BaseRes<>(BaseErrorEnum.SYSTEM_ERROR.getErrorCode(),
                BaseErrorEnum.SYSTEM_ERROR.getErrorMsg(), null);
    }

    public static BaseRes<String> ofFail(BaseException e) {
        return new BaseRes<>(e.getErrorCode(), e.getErrorMsg(), null);
    }

}
