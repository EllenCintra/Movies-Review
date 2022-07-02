package com.movies.model.dto;

import org.springframework.data.domain.Page;

import com.movies.model.MovieComment;

public class MovieCommentsDto {

	private Page<CommentDto> comments;
	
	public MovieCommentsDto() {

	}
	
	public MovieCommentsDto(Page<MovieComment> comments) {
		this.comments = comments.map(CommentDto::new);
	}

	
	public Page<CommentDto> getComments() {
		return comments;
	}
}
