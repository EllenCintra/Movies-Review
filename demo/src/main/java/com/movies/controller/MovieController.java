package com.movies.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movies.client.omdb.MoviesSearchDto;
import com.movies.model.Account;
import com.movies.model.dto.MovieAssessmentsDto;
import com.movies.model.dto.MovieCommentsDto;
import com.movies.model.enums.Profile;
import com.movies.model.form.AssessmentForm;
import com.movies.model.form.CommentForm;
import com.movies.service.MovieService;

@RestController
@RequestMapping("/movies")
@CrossOrigin("http://localhost:8080")
public class MovieController {
	
	private MovieService movieService;
	
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}
	
	//Search movie by title
	@GetMapping("/title/{title}")
	public ResponseEntity<MoviesSearchDto> searchMovie(@PathVariable @NotEmpty @Valid String title) {
		return ResponseEntity.status(HttpStatus.OK).body(movieService.searchMovie(title));
	}
	
	//Post an assessment
	@PostMapping("/assessments/{imdbID}")
	@CacheEvict(value = "movieAssessments", allEntries=true)
	public ResponseEntity<Object> postAssessment(@PathVariable String imdbID, @AuthenticationPrincipal Account logged, @RequestBody @Valid AssessmentForm dto) {
		movieService.postAssessment(imdbID, logged, dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
	
	//Post a comment
	@PostMapping("/comments/{imdbID}")
	@CacheEvict(value = "movieComments", allEntries=true)
	public ResponseEntity<Object> postComment(@PathVariable String imdbID, @AuthenticationPrincipal Account logged, @RequestBody @Valid CommentForm dto) {
		if (logged.getProfile() == Profile.Reader) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		
		movieService.postComment(imdbID, logged, dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
	
	//Get movie assessments by imdbID
	@GetMapping("/{imdbID}/assessments")
	@Cacheable(value = "movieAssessments")
	public ResponseEntity<MovieAssessmentsDto> getMovieAssessments(@PathVariable  String imdbID,  @PageableDefault(sort = "createdAt", page = 0, size = 10) Pageable pagination) {
		MovieAssessmentsDto assessments = movieService.getMovieAssessmentsDto(imdbID, pagination);
		return ResponseEntity.status(HttpStatus.OK).body(assessments);
	}

	//Get movie comments by imdbID
	@GetMapping("/{imdbID}/comments")
	@Cacheable(value = "movieComments")
	public ResponseEntity<MovieCommentsDto> getMovieComments(@PathVariable  String imdbID,  @PageableDefault(sort = "createdAt", page = 0, size = 10) Pageable pagination) {
		return ResponseEntity.status(HttpStatus.OK).body(movieService.getMovieCommentsDto(imdbID, pagination));
	}
	
}
