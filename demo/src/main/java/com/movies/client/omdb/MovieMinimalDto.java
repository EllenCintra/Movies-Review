package com.movies.client.omdb;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieMinimalDto {

    @JsonProperty("imdbID")
    private String imdbId;
    
    @JsonProperty("Title")
    private String title;
    
    @JsonProperty("Year")
    private Integer year;
    
    public void setYear(String year) {
        this.year = convertYear(year);
    }

    private int convertYear(final String year) {
        if (year.matches("\\d+")) {
            return Integer.parseInt(year);
        }
        return Arrays.stream(year.split("\\D"))
            .map(Integer::parseInt)
            .findFirst()
            .orElseThrow();
    }
}
