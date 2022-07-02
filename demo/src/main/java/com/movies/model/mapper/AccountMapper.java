package com.movies.model.mapper;

import com.movies.model.Account;
import com.movies.model.dto.AccountIdNameDto;
import com.movies.model.form.AccountForm;

public class AccountMapper {
	
	public static Account ofDto(AccountForm form) {
		Account account = new Account(form.getName(),form.getUsername(), form.getPassword());
		return account;
	}

	public static AccountIdNameDto toDto(Account account) {
		return new AccountIdNameDto(account.getId(), account.getName());
	}
}
