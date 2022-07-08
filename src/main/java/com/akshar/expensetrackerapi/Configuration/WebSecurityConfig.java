package com.akshar.expensetrackerapi.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Deprecated
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/login", "register").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic();
    }
}
