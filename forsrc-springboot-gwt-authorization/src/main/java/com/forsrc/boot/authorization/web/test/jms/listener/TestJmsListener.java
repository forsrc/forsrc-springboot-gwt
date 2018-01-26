package com.forsrc.boot.authorization.web.test.jms.listener;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TestJmsListener {

    @JmsListener(destination = "jms/queues/test")
    public void onMessage(TextMessage message) throws JMSException {
        System.out.println("----> " + message);
        System.out.println("  --> " + message.getText());
    }

    //@JmsListener(destination = "jms/queues/test")
    public void onMapMessage(MapMessage message) throws JMSException {
        System.out.println("----> " + message);
        System.out.println("  --> " + message.getString("id"));
    }
}
