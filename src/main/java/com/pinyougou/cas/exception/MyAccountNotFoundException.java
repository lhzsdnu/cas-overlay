package com.pinyougou.cas.exception;

import javax.security.auth.login.AccountException;

/**
 * @description: 用户没找到异常
 */
public class MyAccountNotFoundException extends AccountException {
    public MyAccountNotFoundException() {
        super();
    }

    public MyAccountNotFoundException(String msg) {
        super(msg);
    }
}
