package com.psy.demo.utils.exception;

/**
 * @author lianup
 */
public class NotFoundException extends WechatPayException {


    private static final long serialVersionUID = -1981192537895425762L;

    public NotFoundException(String message) {
        super(message);
    }
}
