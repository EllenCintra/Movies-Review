package com.movies.model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.movies.model.Comment;
import com.movies.model.mapper.AccountMapper;

public class CommentDto {

	private Long id;
	private AccountIdNameDto author;
	private String comment;
	private LocalDateTime createdAt = LocalDateTime.now();
	private List<CommentLikesDto> likes = new ArrayList<>();
	private List<CommentDeslikesDto> deslikes = new ArrayList<>();
	private List<CommentDto> replies = new ArrayList<>();
	private boolean repeated;

	public CommentDto() {

	}

	public CommentDto(Comment comment) {
		this.id = comment.getId();
		this.author = AccountMapper.toDto(comment.getUser());
		this.comment = comment.getComment();
		this.createdAt = comment.getCreatedAt();
		this.likes = comment.getLikes().stream().map(CommentLikesDto::new).collect(Collectors.toList());
		this.deslikes = comment.getDeslikes().stream().map(CommentDeslikesDto::new).collect(Collectors.toList());
		this.repeated = comment.isRepeated();
	}

	public Long getId() {
		return id;
	}

	public AccountIdNameDto getUser() {
		return author;
	}

	public String getComment() {
		return comment;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public List<CommentLikesDto> getLikes() {
		return likes;
	}

	public List<CommentDeslikesDto> getDeslikes() {
		return deslikes;
	}
	
	public List<CommentDto> getReplies() {
		return replies;
	}

	public boolean isRepeated() {
		return repeated;
	}
}
