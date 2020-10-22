package com.forsrc.boot.authorization;

import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication
@EnableAuthorizationServer
@EnableConfigurationProperties({ AuthorizationServerProperties.class })
//@EnableRedisHttpSession
public class AuthorizationApplication {

    /*
    @Bean
    public PropertySourcesPlaceholderConfigurer properties() {

        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource[] { new ClassPathResource("*.yml")});
        propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
        return propertySourcesPlaceholderConfigurer;
    }
    */

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationApplication.class, args);
    }
    
    @Bean
    @Order(-1000)
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

    //@Profile("!cloud")
    //@Bean
    RequestDumperFilter requestDumperFilter() {
        return new RequestDumperFilter();
    }
}
