package com.movies.model.dto;

import com.movies.model.CommentLikes;
import com.movies.model.mapper.AccountMapper;

public class CommentLikesDto {
	private AccountIdNameDto user;
	
	public AccountIdNameDto getUser() {
		return user;
	}

	public CommentLikesDto(CommentLikes like) {
		this.user =  AccountMapper.toDto(like.getUser());
	}
}
