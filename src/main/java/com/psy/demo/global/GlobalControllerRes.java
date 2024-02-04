package com.psy.demo.global;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.psy.demo.enums.BaseErrorEnum;
import com.psy.demo.vo.res.BaseRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

@ControllerAdvice
@Slf4j
public class GlobalControllerRes implements ResponseBodyAdvice<Object> {

    @ExceptionHandler(value = Exception.class)
    public @ResponseBody
    BaseRes handle(Exception e) {
        log.error("全局错误e:" + e.getMessage(), e);
        if (e instanceof BaseException) {
            return BaseRes.ofFail((BaseException) e);
        }
        return BaseRes.ofCommonFail(e);
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        Class<?> returnClass = Objects.requireNonNull(returnType.getMethod()).getReturnType();
        if (body instanceof BaseRes) {
            return body;
        }
        if (body instanceof String || Objects.equals(returnClass, String.class)) {
            return BaseRes.ofSuccess(body);
        }
        return BaseRes.ofSuccess(body);
    }
}
