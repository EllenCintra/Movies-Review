package com.movies.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Assessments")
public class Assessment {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private Movie movie;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private Account author;
	
	private Integer assessment;
	
	private LocalDateTime createdAt = LocalDateTime.now();
	
	public Assessment() {
	} 
	
	public Assessment(Movie movie, Account author, Integer grade) {
		this.movie = movie;
		this.author = author;
		this.assessment = grade;
	}

	public Long getId() {
		return id;
	}

	public Movie getMovie() {
		return movie;
	}

	public Account getUser() {
		return author;
	}

	public Integer getAssessment() {
		return assessment;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

}
