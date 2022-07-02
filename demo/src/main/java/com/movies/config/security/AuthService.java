package com.movies.config.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.movies.model.Account;
import com.movies.repository.AccountRepository;

@Service
public class AuthService implements UserDetailsService{
	
	private AccountRepository accountRepository;
	
	public AuthService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Account> account = accountRepository.findByUsername(username);
		if (account.isPresent()) {
			return account.get();
		}
		
		throw new UsernameNotFoundException("Failed request: invalid username");
	}
}