package com.psy.demo.global;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.psy.demo.controller.LoginController;
import com.psy.demo.controller.TestController;
import com.psy.demo.enums.BaseErrorEnum;
import com.psy.demo.vo.res.BaseRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

@ControllerAdvice
@Slf4j
public class GlobalControllerRes implements ResponseBodyAdvice<Object> {
    private static final String mp3ReturnType = "org.springframework.http.ResponseEntity<org.springframework.core.io.Resource>";

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        log.info(returnType.toString());
        return !(returnType.getParameterType().isAssignableFrom(BaseRes.class)
                || returnType.hasMethodAnnotation(NotGlobalControllerAdvice.class));
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
