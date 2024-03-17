package com.spring_security.configurations.security.filters;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.spring_security.configurations.security.authentication.ApiKeyAuthentication;
import com.spring_security.configurations.security.managers.CustomAuthenticationManager;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiKeyAuthenticationFilter extends OncePerRequestFilter {

	private final String secretKey;

	@Override
	protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request,
			jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain)
			throws jakarta.servlet.ServletException, IOException {
		String inputKey = request.getHeader("key");
		if (inputKey == null) {
			filterChain.doFilter(request, response);
		}

		// authentication that is not yet authorized
		ApiKeyAuthentication authentication = new ApiKeyAuthentication(inputKey, false);

		// delegating the authentication to AuthenticationManager
		try {
			authentication = (ApiKeyAuthentication) new CustomAuthenticationManager(secretKey)
					.authenticate(authentication);
			if (authentication.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(authentication);
				filterChain.doFilter(request, response);
			} else {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}
		} catch (AuthenticationException ex) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}

	}

}
