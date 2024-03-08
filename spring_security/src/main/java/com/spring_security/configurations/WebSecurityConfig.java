package com.spring_security.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class WebSecurityConfig {

	@Bean
	public UserDetailsService userDetailService() {
		InMemoryUserDetailsManager udServiceManager = new InMemoryUserDetailsManager();
		
		//creating a user, with details
		UserDetails user = User.withUsername("userName").password("password").authorities("read").build();

		// Placing the created user into the UserDetailService 
		udServiceManager.createUser(user);

		return udServiceManager;
	}

	/**
	 * Although NoPasswordEncoder is depricated and not used in real world
	 * applications we use it here as often found in the examples
	 * 
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();

	}
}
