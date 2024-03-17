package com.spring_security.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.spring_security.configurations.security.filters.ApiKeyAuthenticationFilter;

@Configuration
public class SecurityConfiguration {
	
	/*
	 * we use the @Value annotation in a class whose constructor will be called by the Spring framework
	 */
	@Value("${secret.request-authentication.key}")
	private String secretKey;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.httpBasic()
				.and()
				.addFilterBefore(new ApiKeyAuthenticationFilter(secretKey), BasicAuthenticationFilter.class)
				.authorizeRequests().anyRequest().authenticated()
				.and().build();
	}

}
