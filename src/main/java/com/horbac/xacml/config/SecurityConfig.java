package com.horbac.xacml.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecurityConfig {

	@Autowired
	protected XACMLAuthorizationManager xacmlAuthManager;

	@Bean
	SecurityFilterChain web(HttpSecurity http) throws Exception {
		http.cors().and().formLogin().and().authorizeHttpRequests((authorize) -> authorize.anyRequest().access(xacmlAuthManager))
        ;
		return http.build();
	}
	@Bean
	UserDetailsService users() {
		UserDetails user1 = User.builder().username("myuser").password("mypass").roles("myrole").build();
		UserDetails user2 = User.builder().username("myuser2").password("{noop}mypass2").roles("myrole2").build();
		return new InMemoryUserDetailsManager(user1, user2);
	}

}