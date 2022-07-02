package com.movies.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Comments")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE")
public abstract class Comment {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	protected Movie movie;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	protected Account author;
	
	protected String comment;
	
	protected LocalDateTime createdAt = LocalDateTime.now();
	
	@OneToMany(mappedBy = "comment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	protected List<CommentLikes> likes = new ArrayList<>();
	
	@OneToMany(mappedBy = "comment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	protected List<CommentDeslikes> deslikes = new ArrayList<>();
	
	protected boolean repeated = false;

	public Comment() {
		
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

	public String getComment() {
		return comment;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public List<CommentLikes> getLikes() {
		return likes;
	}

	public void addLike(CommentLikes like) {
		likes.add(like);
	}

	public List<CommentDeslikes> getDeslikes() {
		return deslikes;
	}

	public void addDeslike(CommentDeslikes deslike) {
		deslikes.add(deslike);
	}

	public boolean isRepeated() {
		return repeated;
	}
	
	public void markAsRepeated() {
		this.repeated = true;
	}

}
