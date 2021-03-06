package com.forsrc.boot.authorization.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;


@Configuration
@EnableResourceServer
@Order(-10)
@AutoConfigureAfter(OAuth2AuthorizationConfig.class)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/api/**")
                        .access("#oauth2.hasScope('read')")
                    .antMatchers(HttpMethod.POST, "/api/**")
                        .access("#oauth2.hasScope('write')")
                .and()
                    .authorizeRequests()
                    .antMatchers("/static/**")
                    .permitAll();
    }
}