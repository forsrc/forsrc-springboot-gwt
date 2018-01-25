package com.forsrc.boot.authorization.web.test.jms.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TestJmsListener {

	@JmsListener(destination = "jms/queues/test")
	public void onMessage(String content) {
		System.out.println("----> " + content);
	}

}
