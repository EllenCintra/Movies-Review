package com.movies.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movies.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{
	
	Optional<Movie> findByImdbID(String imdbID);
}
