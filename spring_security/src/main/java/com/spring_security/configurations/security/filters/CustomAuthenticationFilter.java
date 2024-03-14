package com.spring_security.configurations.security.filters;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.spring_security.configurations.security.authentication.KeyBasedCustomAuthentication;
import com.spring_security.configurations.security.managers.CustomAuthenticationManager;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {
	
	private final CustomAuthenticationManager customAuthManager;
	
	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain)throws ServletException, IOException {
						
		//1. Create an authentication which is not yet authenticated 
		String inputKey = request.getHeader("key");
		KeyBasedCustomAuthentication auth = new KeyBasedCustomAuthentication(inputKey, false);
		
		//2. Delegate the authentication obj to the manager and get back the authentication from the manager
		var authManager = customAuthManager.authenticate(auth);
		
		
		//4. If authenticated then send the request to the next filter in the filter chain
		if(authManager.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(authManager);
			filterChain.doFilter(request, response); //propogate the filter ahead only when authentication is successful
		}
		
	}

}
