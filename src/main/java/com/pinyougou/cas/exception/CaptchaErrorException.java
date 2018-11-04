package com.pinyougou.cas.exception;

import javax.security.auth.login.AccountException;

/**
 * @description: 密码错误异常类
 */
public class CaptchaErrorException extends AccountException {

    public CaptchaErrorException() {
        super();
    }

    public CaptchaErrorException(String msg) {
        super(msg);
    }
}
