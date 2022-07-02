package com.movies.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movies.model.CommentDeslikes;

public interface CommentDeslikesRepository extends JpaRepository<CommentDeslikes, Long> {

}
