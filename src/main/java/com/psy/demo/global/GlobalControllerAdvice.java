package com.psy.demo.global;

import com.psy.demo.vo.res.BaseRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvice implements ResponseBodyAdvice<Object>, WebMvcConfigurer {
    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public void printResponseBodyAdvices() {
        // 获取所有ResponseBodyAdvice的bean
        Map<String, ResponseBodyAdvice> responseBodyAdvices = applicationContext.getBeansOfType(ResponseBodyAdvice.class);

        // 打印所有ResponseBodyAdvice的实现类名
        responseBodyAdvices.forEach((name, advice) -> {
            System.out.println("ResponseBodyAdvice bean name: " + name);
            System.out.println("ResponseBodyAdvice class: " + advice.getClass().getName());
        });
    }

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
        if (body instanceof BaseRes) {
            return body;
        }
        if (String.class.isAssignableFrom(returnType.getParameterType())) {
            return  body;
        }
        return BaseRes.ofSuccess(body);
    }
}
