package com.movies.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movies.model.CommentLikes;

public interface CommentLikesRepository extends JpaRepository<CommentLikes, Long> {

}
