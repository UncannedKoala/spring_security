package com.spring_security.configurations.security.managers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.spring_security.configurations.security.providers.ApiKeyAuthenticationProvider;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

	private String secretKey;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		ApiKeyAuthenticationProvider apiKeyAuthProvider = new ApiKeyAuthenticationProvider(secretKey);
		if (apiKeyAuthProvider.supports(authentication.getClass()))
			return apiKeyAuthProvider.authenticate(authentication);
		return authentication;
	}

}
