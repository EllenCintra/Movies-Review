package com.movies.client.omdb;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "movieMinimalRest", url = "${omdbapi.url}")
public interface MovieMinimalRestRepository {

	 @GetMapping
	 MoviesSearchDto searchByTitle(@RequestParam("s") String movieTitle);
	 
}
