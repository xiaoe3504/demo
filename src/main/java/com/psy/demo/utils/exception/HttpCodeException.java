package com.psy.demo.utils.exception;

/**
 * @author lianup
 */
public class HttpCodeException extends WechatPayException {


    private static final long serialVersionUID = -1981192537895425762L;

    public HttpCodeException(String message) {
        super(message);
    }
}
