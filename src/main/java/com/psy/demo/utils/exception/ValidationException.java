package com.psy.demo.utils.exception;

/**
 * @author lianup
 */
public class ValidationException extends WechatPayException {


    private static final long serialVersionUID = -3473204321736989263L;


    public ValidationException(String message) {
        super(message);
    }
}
