package com.pinyougou.cas.config;

import com.pinyougou.cas.exception.CaptchaErrorException;
import com.pinyougou.cas.exception.MyAccountNotFoundException;
import com.pinyougou.cas.service.UserService;
import org.apereo.cas.authentication.AuthenticationHandlerExecutionResult;
import org.apereo.cas.authentication.Credential;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.handler.support.AbstractPreAndPostProcessingAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.GeneralSecurityException;
import java.util.Map;

public class UsernamePasswordCaptchaAuthenticationHandler extends AbstractPreAndPostProcessingAuthenticationHandler {


    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UsernamePasswordCaptchaAuthenticationHandler(String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order) {
        super(name, servicesManager, principalFactory, order);
    }

    @Override
    protected AuthenticationHandlerExecutionResult doAuthentication(Credential credential) throws GeneralSecurityException, PreventedException {
        UsernamePasswordCaptchaCredential myCredential = (UsernamePasswordCaptchaCredential) credential;

        String username = myCredential.getUsername();
        Map<String, Object> user = userService.findByUserName(username);

        if (user == null) {
            throw new MyAccountNotFoundException("用户不存在");
        }

        //对浏览器传过来密码进行密码加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        //可以在这里直接对用户名校验,或者调用 CredentialsMatcher 校验
        if (!user.get("password").equals(passwordEncoder.encode(myCredential.getPassword()))) {
            throw new CaptchaErrorException("密码错误！");
        }

        return createHandlerResult(credential, this.principalFactory.createPrincipal(username));
    }

    @Override
    public boolean supports(Credential credential) {
        return credential instanceof UsernamePasswordCaptchaCredential;
    }
}
