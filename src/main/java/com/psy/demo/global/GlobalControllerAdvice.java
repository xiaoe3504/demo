package com.psy.demo.global;

import com.psy.demo.vo.res.BaseRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
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
