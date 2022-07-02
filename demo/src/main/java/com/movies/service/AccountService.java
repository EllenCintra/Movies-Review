package com.movies.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.movies.exceptions.BadRequestException;
import com.movies.exceptions.ResourceNotFoundException;
import com.movies.exceptions.UnprocessableEntityException;
import com.movies.model.Account;
import com.movies.model.form.AccountForm;
import com.movies.model.mapper.AccountMapper;
import com.movies.repository.AccountRepository;

@Service
public class AccountService {
	
	private AccountRepository accountRepository;
	private PasswordEncoder encoder;
	
	public AccountService(AccountRepository accountRepository, PasswordEncoder encoder) {
		this.accountRepository = accountRepository;
		this.encoder = encoder;
	}
	
	public void createAccount(@Valid AccountForm accountForm) {
		Optional<Account> user = accountRepository.findByUsername(accountForm.getUsername());
		if (user.isPresent()) {
			throw new UnprocessableEntityException("Username is already in use");
		}
		
		if (!passwordIsValid(accountForm.getPassword())){
			throw new BadRequestException("The password must be between 6 and 20 characters, at least one uppercase and lowercase letter, a number and a special character !@#$%&*");
		}
		
		accountForm.setPassword(encoder.encode(accountForm.getPassword()));
		Account account = AccountMapper.ofDto(accountForm);
		accountRepository.save(account);
	}

	public void setToModeratorProfile(Long userId) {
		Optional<Account> user = accountRepository.findById(userId);
		if (user.isEmpty()) {
			throw new ResourceNotFoundException("User not found");
		}
		
		user.get().setToModeratorProfile();
		accountRepository.save(user.get());
	}
	
	public boolean passwordIsValid(String password) {
		return password.matches("^(?=.*[A-Z])(?=.*[!@#$%&*])(?=.*[0-9])(?=.*[a-z]).{6,20}$");
	}

}
