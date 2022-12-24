package com.millky.blog.application.configuration;

import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig { // } extends WebSecurityConfigurerAdapter {

    List<String> publicApis = List.of("/generate/**", "validated/**");



    //	@Override
    @Bean
//    public
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

                .requestMatchers(new AntPathRequestMatcher("/**/write*")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/**/edit*")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/**/delete*")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
//                .antMatchers("/**/write*", "/**/edit*", "/**/delete*").authenticated()
//                        .antMatchers("/**").permitAll()
                .and()
                    .build();
        // @formatter:on
    }

    //	@Override
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {// void configure(WebSecurity web) throws Exception {
        return (web) -> web.ignoring() .requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
//                .requestMatchers(new AntPathRequestMatcher("/**"))
//                .requestMatchers(new AntPathRequestMatcher("/swagger-ui.html"));
                //antMatchers("/h2-console/**");
    }
}
