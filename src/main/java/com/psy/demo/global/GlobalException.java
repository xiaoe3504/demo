package com.psy.demo.global;

import com.psy.demo.vo.res.BaseRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalException extends RuntimeException {

    @ExceptionHandler(value = Exception.class)
    public BaseRes<String> handle(Exception e) {
        log.error("全局错误e:" + e.getMessage(), e);
        if (e instanceof BaseException) {
            return BaseRes.ofFail((BaseException) e);
        }
        return BaseRes.ofCommonFail(e);
    }
}
