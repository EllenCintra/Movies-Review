package com.movies.model.dto;

import com.movies.model.CommentDeslikes;
import com.movies.model.mapper.AccountMapper;

public class CommentDeslikesDto {
	private AccountIdNameDto user;
	
	public AccountIdNameDto getUser() {
		return user;
	}
	
	public CommentDeslikesDto(CommentDeslikes deslike) {
		this.user =  AccountMapper.toDto(deslike.getUser());
	}
}
