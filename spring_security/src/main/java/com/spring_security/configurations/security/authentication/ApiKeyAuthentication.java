package com.spring_security.configurations.security.authentication;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiKeyAuthentication implements Authentication {

	private final String key;
	private boolean isAuthenticated;

	@Override
	public boolean isAuthenticated() {
		return this.isAuthenticated;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		this.isAuthenticated = isAuthenticated;
	}

	/*
	 * After the API-key authentication is completed, the next authentication in this project implement is 
	 * BasicAuthentication. So when BasicAuthentication.doFilterInternal() executes it
	 * calls BasicAuthentication.authenticationIsRequired(), which will return
	 * 'true' if either of the 3 conditions meet: 
	 * 	1. When there is not Authentication preceding it BasicAuthentication. 
	 * 	2. When the previous authentication Principal name is different from the provided username. 
	 * 	3. The existing authentication (from previous filter in the chain) has
	 * isAuthenticated as 'true'. To get the Principal name ApiKeyAuthentication.getName() is called 
	 * and .equals() is called on the returned value thus, if this method is returning null there will 
	 * be a null pointer exception.
	 */
	@Override
	public String getName() {
		return "";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}
}
