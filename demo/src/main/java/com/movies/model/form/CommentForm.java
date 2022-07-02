package com.movies.model.form;

import javax.validation.constraints.NotBlank;

public class CommentForm {

	@NotBlank
	private String comment;

	public String getComment() {
		return comment;
	}
}
