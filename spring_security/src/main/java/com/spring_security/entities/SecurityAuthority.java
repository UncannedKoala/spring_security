package com.spring_security.entities;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class SecurityAuthority implements GrantedAuthority {

	private final Authority authority;
	
	@Override
	public String getAuthority() {
		return this.authority.getName();
	}

}
