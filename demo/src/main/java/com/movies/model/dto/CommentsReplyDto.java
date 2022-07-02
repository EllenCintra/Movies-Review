package com.movies.model.dto;

import org.springframework.data.domain.Page;

import com.movies.model.CommentReply;

public class CommentsReplyDto {

private Page<CommentDto> comments;
	
	public CommentsReplyDto() {

	}
	
	public CommentsReplyDto(Page<CommentReply> comments) {
		this.comments = comments.map(CommentDto::new);
	}

	public Page<CommentDto> getComments() {
		return comments;
	}
}
