package com.movies.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.movies.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	Page<Comment> findByMovieId(Long movieId, Pageable pagination);

}
