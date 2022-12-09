package com.millky.blog.application.configuration;

import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
@EnableWebSecurity
public class SecurityConfig { // } extends WebSecurityConfigurerAdapter {

    //	@Override
    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        // @formatter:off
        return http
                .oauth2Login()
                    .loginPage("/user/login")
                .and()
                    .logout()
                        .logoutUrl("/user/logout")
                        .logoutSuccessUrl("/")
                .and()
                    .authorizeRequests()
                        .antMatchers("/**/write*", "/**/edit*", "/**/delete*").authenticated()
                        .antMatchers("/**").permitAll()
                .and()
                    .build();
        // @formatter:on
    }

    //	@Override
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {// void configure(WebSecurity web) throws Exception {
        return (web) -> web.ignoring().antMatchers("/h2-console/**");
    }
}
