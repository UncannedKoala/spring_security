package com.spring_security.configurations.security.managers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.spring_security.configurations.security.providers.KeyBasedAuthenticationProvider;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager{

	private final KeyBasedAuthenticationProvider keyBasedAuthenticationProvider ;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if(this.keyBasedAuthenticationProvider.supports(authentication.getClass())) {
			return keyBasedAuthenticationProvider.authenticate(authentication);
		}
		throw new BadCredentialsException("Invalid key! @ CustomAuthenticationManager.authenticate()");
	}
	

}
