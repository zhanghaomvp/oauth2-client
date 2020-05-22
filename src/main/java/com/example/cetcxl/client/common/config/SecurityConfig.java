package com.example.cetcxl.client.common.config;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

import java.io.PrintWriter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    ClientRegistrationRepository clientRegistrationRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(
                        (req, res, auth) -> {
                            Object principal = auth.getPrincipal();
                            res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                            PrintWriter out = res.getWriter();
                            out.write(JSON.toJSONString(principal));
                            out.flush();
                            out.close();
                        }
                )
                .and()
                .oauth2Login().permitAll()
                .and()
                .oauth2Client().clientRegistrationRepository(clientRegistrationRepository)
                .and()
        ;
    }

}
