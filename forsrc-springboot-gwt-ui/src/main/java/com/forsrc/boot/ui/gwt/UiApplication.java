package com.forsrc.boot.ui.gwt;

import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication
@EnableZuulProxy
@EnableOAuth2Sso
//@EnableRedisHttpSession
public class UiApplication {


    public static void main(String[] args) {
        SpringApplication.run(UiApplication.class, args);
    }

//    @Profile("!cloud")
//    @Bean
    RequestDumperFilter requestDumperFilter() {
        return new RequestDumperFilter();
    }

    @Bean
    @Order(-1000)
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }
}
