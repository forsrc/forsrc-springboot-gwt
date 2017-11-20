package com.forsrc.boot.authorization.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;


@Component
public class LoginEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(final String message) {
        System.out.println("--> Publishing LoginApplicationEvent: " + message);
        LoginApplicationEvent loginApplicationEvent = new LoginApplicationEvent(this, message);
        applicationEventPublisher.publishEvent(loginApplicationEvent);
    }
}
