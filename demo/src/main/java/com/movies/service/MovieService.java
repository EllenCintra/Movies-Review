package com.movies.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.movies.client.omdb.MovieMinimalRestRepository;
import com.movies.client.omdb.MoviesSearchDto;
import com.movies.exceptions.BadRequestException;
import com.movies.exceptions.NoDataFoundException;
import com.movies.model.Account;
import com.movies.model.Assessment;
import com.movies.model.MovieComment;
import com.movies.model.Movie;
import com.movies.model.dto.MovieAssessmentsDto;
import com.movies.model.dto.MovieCommentsDto;
import com.movies.model.form.AssessmentForm;
import com.movies.model.form.CommentForm;
import com.movies.repository.AccountRepository;
import com.movies.repository.AssessmentRepository;
import com.movies.repository.CommentRepository;
import com.movies.repository.MovieCommentRepository;
import com.movies.repository.MovieRepository;

@Service
public class MovieService {
	
	private MovieMinimalRestRepository movieMinimalRepository;
	private MovieRepository movieRepository;
	private AssessmentRepository assessmentRepository;
	private CommentRepository commentRepository;
	private MovieCommentRepository movieCommentRepository;
	private AccountRepository accountRepository;
	
	public MovieService (MovieMinimalRestRepository movieMinimal, MovieRepository movieRepository, 
			AssessmentRepository assessmentRepository, CommentRepository commentRepository, 
			MovieCommentRepository movieCommentRepository, AccountRepository accountRepository) {
		
		this.movieMinimalRepository = movieMinimal;
		this.movieRepository = movieRepository;
		this.assessmentRepository = assessmentRepository;
		this.commentRepository = commentRepository;
		this.accountRepository = accountRepository;
		this.movieCommentRepository = movieCommentRepository;
	}

	public MoviesSearchDto searchMovie(String title) {
		MoviesSearchDto movies = movieMinimalRepository.searchByTitle(title);
		if (movies.getTotal() == null) {
			throw new NoDataFoundException("No movies found with that title");
		}
		return movies;
	}

	public void postAssessment(String imdbID, Account author, AssessmentForm dto) {
		if (!assessmentIsValid(dto.getAssessment())) {
			throw new BadRequestException("The grade must be an integer from 1 to 5");
		}
		
		Movie movie = getMovie(imdbID);
		if (movie.getId() == null) {
			movie = movieRepository.save(movie);
		}
		
		Assessment assessment = new Assessment(movie, author, Integer.parseInt(dto.getAssessment()));
		
		assessmentRepository.save(assessment);
		
		author.addPoint();
		accountRepository.save(author);
	}
	
	public boolean assessmentIsValid(String assessment) {	
		return assessment.matches("[1-5]");
	}
									
	public void postComment(String imdbID, Account author, CommentForm dto) {
		Movie movie = getMovie(imdbID);
		if (movie.getId() == null) {
			movie = movieRepository.save(movie);
		}
		
		MovieComment comment = new MovieComment(movie, author, dto.getComment());
		commentRepository.save(comment);
		
		author.addPoint();
		accountRepository.save(author);
	}
	
	public MovieAssessmentsDto getMovieAssessmentsDto(String imdbID, Pageable pagination) {
		Movie movie = getMovie(imdbID);
		if (movie.getId() == null) {
			return new MovieAssessmentsDto();
		}
		
		Page<Assessment> assessments = assessmentRepository.findByMovieId(movie.getId(), pagination); 
		MovieAssessmentsDto dto = new MovieAssessmentsDto(movie, assessments);		
		float avgAssessments = assessmentRepository.calculateAverageOfEvaluations(movie.getId());
		dto.setAvgAssessments(avgAssessments);
		
		return dto;
	}
	
	public MovieCommentsDto getMovieCommentsDto(String imdbID, Pageable pagination) {
		Movie movie = getMovie(imdbID);
		if (movie.getId() == null) {
			return new MovieCommentsDto();
		}
		
		Page<MovieComment> comments = movieCommentRepository.findByMovieId(movie.getId(), pagination);
		MovieCommentsDto dto = new MovieCommentsDto(comments);
		return dto;
	}
	
	//Check if movie has already been saved
	private Movie getMovie(String imdbID) {
		Optional<Movie> movieSaved = movieRepository.findByImdbID(imdbID);	
		if (movieSaved.isPresent()) {
			return movieSaved.get();	
		}
		return new Movie(imdbID);
	}

}
