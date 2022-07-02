package com.movies.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.movies.model.Account;
import com.movies.repository.AccountRepository;

public class TokenFilterAuthentication extends OncePerRequestFilter {
	
	private TokenService tokenService;
	private AccountRepository accountRepository;

	public TokenFilterAuthentication(TokenService tokenService, AccountRepository accountRepository) {
		this.tokenService = tokenService;
		this.accountRepository = accountRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = tokenService.getToken(request);
		boolean valid = tokenService.isValidToken(token);
		if (valid) {
			clientAuthentication(token);
		}
		//if token is not valid, continue, spring will stop
		filterChain.doFilter(request, response);
	}

	private void clientAuthentication(String token) {
		Long accountID = tokenService.getIdAccount(token);
		Account account = accountRepository.findById(accountID).get();
		//password parameter is null because has already been authenticated by Spring
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(account, null, account.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}


}
