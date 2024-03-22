package com.spring_security.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.spring_security.configurations.security.filters.ApiKeyAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

	@Value("${secret.request-authentication.key}")
	private String key;

	/**
	 * .anyRequest() is a matcher method that Maps any request. <br>
	 * .authenticated() is an authorization method that Specifies that URLs are
	 * allowed by any authenticated user.<br>
	 * Authorization is defined by a set of 2 methods : matcher method + authorization rule<br>
	 * matcher methods : anyRequest(), mvcMatcher(), antMatchers(), regexMatchers()<br>
	 * 
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		System.out.println(key);
		return http.httpBasic().and()
				.addFilterBefore(new ApiKeyAuthenticationFilter(key), BasicAuthenticationFilter.class)

				.authorizeHttpRequests() 			// Allows restricting access based upon the HttpServletRequest using RequestMatcher implementations, returns HttpSecurity for further customizations

				
				.anyRequest().authenticated() 					// authorization rule for any request, allows request if it is authenticated
//				.anyRequest().permitAll() 						// authorization rule for any request, that allows requests with or without authentication, but not with wrong authentication credentials
//				.anyRequest().denyAll()							// authorization rule for any request, that stop all the requests
//				.anyRequest().hasAnyAuthority()					// empty is INVALID! it does require (String ...) representing the authorities which are to be allowed
//				.anyRequest().hasAnyAuthority("read", "write")	// authorization rule for any request, that allows request that have either of the the given authorities
//				.anyRequest().hasAuthority("read")				// authorization rule for any request, that allows request that have 'read' authority
//				.anyRequest().hasAnyRole()						//empty is INVALID! it does require (String ...) representing the roles which are allowed
//				.anyRequest().hasAnyRole("ADMIN", "MANAGER")	//any one of the listed roles is allowed
//				.anyRequest().hasRole("ADMIN")
				
				.and().build();
	}

	@Bean
	UserDetailsService userDetailService() {
		var udm = new InMemoryUserDetailsManager();
		var snape = User.withUsername("Snape").password(passwordEncoder().encode("Boomslang"))
//				.authorities("read", "write")		//just creates a list having a SimpleGrantedAuthority object for each arguments
				.roles("ADMIN")						//roles just creates SimpleGrantedAuthority with role as suffix "ROLE_" to the passed argument, an implementation of GrantedAuthority
				.build();
		var hagrid = User.withUsername("Hagrid").password(passwordEncoder().encode("dragon"))
//				.authorities("write")
				.roles("MANAGER")
				.build();
		var harry = User.withUsername("Harry").password(passwordEncoder().encode("broom"))
//				.authorities("read")
				.roles("USER")
				.build();
		var ron = User.withUsername("Ron").password(passwordEncoder().encode("food"))
//				.authorities("read")
				.roles("USER")
				.build();

		udm.createUser(snape);
		udm.createUser(hagrid);
		udm.createUser(harry);
		udm.createUser(ron);
		return udm;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
