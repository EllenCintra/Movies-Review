package com.movies.controller;

import javax.validation.Valid;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movies.model.Account;
import com.movies.model.dto.CommentsReplyDto;
import com.movies.model.enums.Profile;
import com.movies.model.form.CommentForm;
import com.movies.service.CommentService;

@RestController
@RequestMapping("/comments/{id}")
@CrossOrigin("http://localhost:8080")
public class CommentController {
	
	private CommentService commentService;
	
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	//Reply a comment
	@PostMapping
	public ResponseEntity<Object> replyComment(@PathVariable Long id, @AuthenticationPrincipal Account logged, @RequestBody @Valid CommentForm dto) {
		if (logged.getProfile() == Profile.Reader) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		commentService.postReplyComment(id, logged, dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
	
	//Get comment replies
	@GetMapping
	public ResponseEntity<CommentsReplyDto> getCommentsReply(@PathVariable Long id, @PageableDefault(sort = "createdAt", page = 0, size = 10) Pageable pagination) {
		return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentsReply(id, pagination));
	}
	
	//Like a comment
	@PatchMapping("/like")
	@CacheEvict(value = "movieComments", allEntries=true)
	public ResponseEntity<Object> likeComment(@PathVariable Long id, @AuthenticationPrincipal Account logged) {
		if (logged.getProfile() == Profile.Reader || logged.getProfile() == Profile.Basic) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		commentService.likeComment(id, logged);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	//Deslike a comment
	@PatchMapping("/deslike")
	@CacheEvict(value = "movieComments", allEntries=true)
	public ResponseEntity<Object> deslikeComment(@PathVariable Long id, @AuthenticationPrincipal Account logged) {
		if (logged.getProfile() == Profile.Reader || logged.getProfile() == Profile.Basic) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		commentService.deslikeComment(id, logged);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	//Mark a comment as repeated
	@PatchMapping("/repeated")
	@CacheEvict(value = "movieComments", allEntries=true)
	public ResponseEntity<Object> repeatedComment(@PathVariable Long id, @AuthenticationPrincipal Account logged) {
		if (logged.getProfile() != Profile.Moderator) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		commentService.markAsRepeated(id);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	//Delete a comment by id
	@DeleteMapping
	@CacheEvict(value = "movieComments", allEntries=true)
	public ResponseEntity<Object> deleteComment(@PathVariable Long id, @AuthenticationPrincipal Account logged) {
		if (logged.getProfile() != Profile.Moderator) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		commentService.deleteComment(id);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
}
