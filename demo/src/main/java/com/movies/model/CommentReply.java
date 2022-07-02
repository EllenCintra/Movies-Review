package com.movies.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="CommentReplies")
public class CommentReply extends Comment {
	
	@ManyToOne
	@JoinColumn
	private Comment commentReplied;

	public CommentReply() {
	}
	
	public CommentReply(Comment commentReplied, Account author, String text) {
		this.commentReplied = commentReplied;
		super.movie = commentReplied.getMovie();
		super.author = author;
		super.comment = text;
	}

	public Long getId() {
		return id;
	}

	public Comment commentReplied() {
		return commentReplied;
	}

}
