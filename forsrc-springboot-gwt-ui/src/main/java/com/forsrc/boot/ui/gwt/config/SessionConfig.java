package com.forsrc.boot.ui.gwt.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.stereotype.Component;

@Configuration
public class SessionConfig {

	@Value("${server.session.cookie.name:UI_SESSIONID}")
	private String cookieName;

	@Bean
	public CookieSerializer cookieSerializer() {
		DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
		cookieSerializer.setCookieName(cookieName);
		return cookieSerializer;
	}

	@Component
	public static class SessionFixBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
		private static final String SERIALIZATION_ID = "4086d293-966c-4d89-8485-f1c1f5c09218";

		@Override
		public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
			if ((beanFactory instanceof DefaultListableBeanFactory)) {
				DefaultListableBeanFactory dlbf = (DefaultListableBeanFactory) beanFactory;
				dlbf.setSerializationId(SERIALIZATION_ID);
			}
		}
	}
}
