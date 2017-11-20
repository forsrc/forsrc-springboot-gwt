package com.forsrc.boot.authorization.login;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class LoginSuccessApplicationListener implements ApplicationListener<AuthenticationSuccessEvent>{

    @Autowired
    private LoginEventPublisher loginEventPublisher;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        String message = String.format("\"%s\" login on %s", event.getAuthentication().getName(), new Date());
        System.out.println("--> LoginSuccessApplicationListener:  " + message);
        loginEventPublisher.publishEvent(message);
    }
    

}
