package com.movies.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.movies.model.CommentReply;

public interface CommentReplyRepository extends JpaRepository<CommentReply, Long> {
	
	Page<CommentReply> findByCommentRepliedId(Long id, Pageable pagination);
}
