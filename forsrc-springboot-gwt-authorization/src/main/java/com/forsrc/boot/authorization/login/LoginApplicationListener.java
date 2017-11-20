package com.forsrc.boot.authorization.login;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class LoginApplicationListener implements ApplicationListener<LoginApplicationEvent> {


    @Override
    public void onApplicationEvent(LoginApplicationEvent event) {
        System.out.println("--> Received   LoginApplicationEvent: " + event.getMessage());

    }

}
