package com.movies.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.movies.repository.AccountRepository;

@SuppressWarnings("deprecation")
@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	private AuthService authService;
	private TokenService tokenService;
	private AccountRepository accountRepository;
	
	public SecurityConfigurations (AuthService authService, TokenService tokenService, AccountRepository accountRepository) {
		this.authService = authService;
		this.tokenService = tokenService;
		this.accountRepository = accountRepository;
	}
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**.html\", \"/v2/api-docs\", \"/webjars/**\",\"/configuration/**\", \"/swagger-resources/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/login").permitAll()
		.antMatchers(HttpMethod.POST, "/accounts").permitAll()
		.antMatchers("/h2-console/**").permitAll()
		.anyRequest().authenticated()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().headers().frameOptions().sameOrigin()
		//Get Authorization header token
		.and().addFilterBefore(new TokenFilterAuthentication(tokenService, accountRepository), UsernamePasswordAuthenticationFilter.class);
	}
}
