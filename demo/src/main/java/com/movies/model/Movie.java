package com.movies.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Movies")
public class Movie {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String imdbID;

	
	public Movie(String imdbID) {
		this.imdbID = imdbID;
	}

	public Movie() {
	}
	
	public Long getId() {
		return id;
	}

	public String getImdbID() {
		return imdbID;
	}
}
