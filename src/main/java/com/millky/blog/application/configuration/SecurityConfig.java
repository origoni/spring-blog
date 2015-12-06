package com.millky.blog.application.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
			.formLogin()
				.loginPage("/user/login")
			.and()
				.logout()
					.logoutUrl("/user/logout")
					.deleteCookies("JSESSIONID")
					.logoutSuccessUrl("/post/list")
			.and()
				.authorizeRequests()
					.antMatchers("/**/write*", "/**/edit*", "/**/delete*").authenticated()
					.antMatchers("/**").permitAll();
		// @formatter:on
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/h2-console/**");
	}
}
