package com.movies.model.mapper;

import com.movies.model.MovieComment;
import com.movies.model.dto.CommentDto;

public class CommentMapper {
	
	public static CommentDto toDto(MovieComment comment) {
		return new CommentDto(comment);
	}
}
