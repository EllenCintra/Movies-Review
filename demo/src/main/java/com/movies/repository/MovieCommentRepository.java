package com.movies.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.movies.model.MovieComment;

public interface MovieCommentRepository extends JpaRepository<MovieComment, Long>{

	Page<MovieComment> findByMovieId(Long movieId, Pageable pagination);
}
