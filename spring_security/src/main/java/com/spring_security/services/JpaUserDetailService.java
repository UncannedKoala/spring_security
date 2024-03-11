package com.spring_security.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring_security.entities.SecurityUser;
import com.spring_security.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JpaUserDetailService implements UserDetailsService{

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("JpaUserDetailService.loadUserByUsername()");
		var user = userRepository.findUserByUserName(username);
		System.out.println(user.get());
		return user.map(SecurityUser::new)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found "+username));
	}

}
