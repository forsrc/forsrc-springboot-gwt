package com.forsrc.boot.authorization.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@Order(-20)
public class LoginConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                    .requestMatchers()
                    .antMatchers("/", "/index", "/login", "/oauth/authorize", "/oauth/confirm_access")
                .and()
                    .authorizeRequests()
                    .anyRequest()
                    .authenticated()
                //.and()
                    //.csrf()
                    //.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                    ;
    }
}
