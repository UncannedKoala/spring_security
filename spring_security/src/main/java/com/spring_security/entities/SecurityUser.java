package com.spring_security.entities;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SecurityUser implements UserDetails {

	private final User user;

	@Override
	public String getUsername() {
		return user.getUser_name();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	/**
	 * <li> Granted Authority is a functional interface, thus for now we are using
	 * lambda expression to give an implementation where the functional method only returns "read".
	 * <li> We will later look into what role {@link GrantedAuthority} has in spring security. 
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getAuthority().stream().map(SecurityAuthority::new).collect(Collectors.toList());
	}

	//change the return type of the following methods to true
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
