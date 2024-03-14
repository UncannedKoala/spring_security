package com.spring_security.configurations.security.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.spring_security.configurations.security.authentication.KeyBasedCustomAuthentication;

@Component
public class KeyBasedAuthenticationProvider implements AuthenticationProvider {

	@Value("${secret.request-authentication.key}")
	private String secretKey;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		KeyBasedCustomAuthentication auth = (KeyBasedCustomAuthentication) authentication;

		if (auth.getSecretKey().equals(secretKey)) {
			// here we set key as null because we do not want to expose the secret-key
			return new KeyBasedCustomAuthentication(null, true);
		}
		throw new BadCredentialsException("Invalid key!");
	}

	/**
	 * By calling this method Authentication manager know which provider supports
	 * which Authentication
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return KeyBasedCustomAuthentication.class.equals(authentication);
	}

}
