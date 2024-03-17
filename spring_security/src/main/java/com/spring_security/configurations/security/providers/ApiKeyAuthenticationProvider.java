package com.spring_security.configurations.security.providers;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.spring_security.configurations.security.authentication.ApiKeyAuthentication;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiKeyAuthenticationProvider implements AuthenticationProvider {

//	@Value("${secret.request-authentication.key}")
	private String secretKey;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (authentication == null)
			throw new NullPointerException("argument 'authentication' can not be null");
		ApiKeyAuthentication apiKeytAuth = (ApiKeyAuthentication) authentication;
		if (apiKeytAuth.getKey().equals(secretKey)) {
			apiKeytAuth.setAuthenticated(true);
			return authentication;
		}
		throw new BadCredentialsException("invalid API-key");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return ApiKeyAuthentication.class.equals(authentication);
	}

}
