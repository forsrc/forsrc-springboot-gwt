package com.forsrc.boot.authorization.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
                    .logout()
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true)
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login?logout")
                    .permitAll()
                //.and()
                    //.requestMatchers()
                    //.antMatchers("/", "/login", "/logout", "/oauth/authorize", "/oauth/confirm_access", "/test")
                .and()
                    .authorizeRequests()
                    .antMatchers("/test", "/oauth/token")
                    .permitAll()
                .and()
                    .csrf()
                    .ignoringAntMatchers("/test", "/oauth/token")
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                    .authorizeRequests()
                    .anyRequest().authenticated();
                    ;
    }

    @Bean
    AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPoint() {

            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response,
                    org.springframework.security.core.AuthenticationException authException)
                    throws IOException, ServletException {
                String requestedBy = request.getHeader("X-Requested-By");
                if (requestedBy == null || requestedBy.isEmpty()) {
                    HttpServletResponse httpResponse = (HttpServletResponse) response;
                    httpResponse.addHeader("WWW-Authenticate", "Basic realm=Cascade Realm");
                    httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
                } else {
                    HttpServletResponse httpResponse = (HttpServletResponse) response;
                    httpResponse.addHeader("WWW-Authenticate", "Application driven");
                    httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
                }
            }
        };
    }
}
