package com.movies.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movies.config.security.TokenService;
import com.movies.exceptions.BadRequestException;
import com.movies.model.dto.TokenDto;
import com.movies.model.form.LoginForm;

@RestController
@RequestMapping("/login")
public class AuthController {

	private AuthenticationManager authManager;
	private TokenService tokenService;
	
	public AuthController(AuthenticationManager authManager, TokenService tokenService) {
		this.authManager = authManager;
		this.tokenService = tokenService;
	}

	@PostMapping
	public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid LoginForm loginForm) {
		UsernamePasswordAuthenticationToken loginData = loginForm.convert();
		
		try {
			Authentication authentication = authManager.authenticate(loginData);
			String token = tokenService.createToken(authentication);
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
		}
		catch (AuthenticationException e) {
			throw new BadRequestException("Invalid login or password");
		}
	}
}
