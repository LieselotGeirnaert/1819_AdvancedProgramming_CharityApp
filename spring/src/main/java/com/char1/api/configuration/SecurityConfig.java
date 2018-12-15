package com.char1.api.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        /*security.csrf().disable()
                .authorizeRequests().antMatchers(HttpMethod.OPTIONS, "should be * / without space" ).permitAll()
                .antMatchers(HttpMethod.GET, "/").permitAll();*/
        security.httpBasic().disable();
    }

}
