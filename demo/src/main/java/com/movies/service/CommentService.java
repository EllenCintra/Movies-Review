package com.movies.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.movies.exceptions.ResourceNotFoundException;
import com.movies.model.Account;
import com.movies.model.Comment;
import com.movies.model.CommentDeslikes;
import com.movies.model.CommentLikes;
import com.movies.model.CommentReply;
import com.movies.model.dto.CommentsReplyDto;
import com.movies.model.form.CommentForm;
import com.movies.repository.CommentDeslikesRepository;
import com.movies.repository.CommentLikesRepository;
import com.movies.repository.CommentReplyRepository;
import com.movies.repository.CommentRepository;

@Service
public class CommentService {

	private CommentRepository commentRepository;
	private CommentReplyRepository commentReplyRepository;
	private CommentLikesRepository commentLikesRepository;
	private CommentDeslikesRepository commentDeslikesRepository;
	
	public CommentService (CommentRepository commentRepository, CommentReplyRepository commentReplyRepository, 
			CommentLikesRepository commentLikesRepository, CommentDeslikesRepository commentDeslikesRepository) {
		
		this.commentRepository = commentRepository;
		this.commentReplyRepository = commentReplyRepository;
		this.commentLikesRepository = commentLikesRepository;
		this.commentDeslikesRepository = commentDeslikesRepository;
	}

	public void postReplyComment(Long commentAnsweredId, Account logged, CommentForm dto) {
		Optional<Comment> commentAnswered = commentRepository.findById(commentAnsweredId);
		if (commentAnswered.isEmpty()) {
			throw new ResourceNotFoundException("Comment not found");
		}
		
		CommentReply comment = new CommentReply(commentAnswered.get(), logged, dto.getComment());
		commentRepository.save(comment);
	}
	
	public CommentsReplyDto getCommentsReply(Long id, Pageable pagination) {
		Optional<Comment> comment = commentRepository.findById(id);
		if (comment.isEmpty()) {
			throw new ResourceNotFoundException("Comment not found");
		}
		
		Page<CommentReply> comments = commentReplyRepository.findByCommentRepliedId(comment.get().getId(), pagination);
		CommentsReplyDto dto = new CommentsReplyDto(comments);
		return dto;
	}

	public void likeComment(Long commentId, Account logged) {
		Optional<Comment> comment = commentRepository.findById(commentId);
		if (comment.isEmpty()) {
			throw new ResourceNotFoundException("Comment not found");
		}
		
		CommentLikes like = new CommentLikes(comment.get(), logged);
		commentLikesRepository.save(like);
		
		comment.get().addLike(like);
		commentRepository.save(comment.get());
	}

	public void deslikeComment(Long commentId, Account logged) {
		Optional<Comment> comment = commentRepository.findById(commentId);
		if (comment.isEmpty()) {
			throw new ResourceNotFoundException("Comment not found");
		}
		
		CommentDeslikes deslike = new CommentDeslikes(comment.get(), logged);
		commentDeslikesRepository.save(deslike);
		
		comment.get().addDeslike(deslike);
		commentRepository.save(comment.get());
	}

	public void deleteComment(Long commentId) {
		Optional<Comment> comment = commentRepository.findById(commentId);
		if (comment.isEmpty()) {
			throw new ResourceNotFoundException("Comment not found");
		}
		
		commentRepository.deleteById(commentId);		
	}
	
	public void markAsRepeated(Long commentId) {
		Optional<Comment> comment = commentRepository.findById(commentId);
		if (comment.isEmpty()) {
			throw new ResourceNotFoundException("Comment not found");
		}
		
		comment.get().markAsRepeated();
		commentRepository.save(comment.get());
	}

}
