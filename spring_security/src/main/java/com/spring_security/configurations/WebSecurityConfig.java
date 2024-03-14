package com.spring_security.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.spring_security.configurations.security.filters.CustomAuthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class WebSecurityConfig {

	private final CustomAuthenticationFilter customAuthFilter;

//	authorizehttprequestconfigurer
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.addFilterAt(customAuthFilter, UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.anyRequest()
				.authenticated()
				.and()
				.build();
	}
}