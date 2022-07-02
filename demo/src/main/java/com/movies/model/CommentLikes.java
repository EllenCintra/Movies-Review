package com.movies.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CommentLikes {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private Comment comment;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private Account user;

	public CommentLikes() {

	}

	public CommentLikes(Comment comment, Account user) {
		this.comment = comment;
		this.user = user;
	}

	public Comment getComment() {
		return comment;
	}
	public Account getUser() {
		return user;
	}
}
