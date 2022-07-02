package com.movies.client.omdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoviesSearchDto {

    @JsonProperty("Search")
    private List<MovieMinimalDto> resultList;
    private Integer total;

	public Integer getTotal() {
		return total;
	}

    @JsonProperty("totalResults")
    public void setTotal(String total) {
        this.total = Integer.parseInt(total);
    }
}

