package com.movies.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movies.model.Account;
import com.movies.model.enums.Profile;
import com.movies.model.form.AccountForm;
import com.movies.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	private AccountService accountService;
	
	public AccountController(AccountService accountService, PasswordEncoder encoder) {
		this.accountService = accountService;
	}

	@PostMapping
	public ResponseEntity<Object> createAccount (@RequestBody @Valid AccountForm accountForm) {
		accountService.createAccount(accountForm);
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
	
	@PatchMapping("{id}/profile/moderator")
	public ResponseEntity<Object> setToModeratorProfile (@PathVariable Long id, @AuthenticationPrincipal Account logged) {
		if (logged.getProfile() != Profile.Moderator) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		accountService.setToModeratorProfile(id);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
}
