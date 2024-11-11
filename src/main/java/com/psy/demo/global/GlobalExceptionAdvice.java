package com.psy.demo.global;

import com.psy.demo.vo.res.BaseRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = BaseException.class)
    public BaseRes<String> handle(BaseException e) {
        log.error("捕获的全局错误:" + e.getMessage(), e);
        return BaseRes.ofFail(e);
    }
}
