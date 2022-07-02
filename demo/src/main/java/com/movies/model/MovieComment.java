package com.movies.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="MovieComments")
public class MovieComment extends Comment {

	public MovieComment() {
	}
	
	public MovieComment(Movie movie, Account author, String text) {
		super.movie = movie;
		super.author = author;
		super.comment = text;
	}
}
