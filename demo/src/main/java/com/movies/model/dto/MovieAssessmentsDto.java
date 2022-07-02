package com.movies.model.dto;

import org.springframework.data.domain.Page;

import com.movies.model.Assessment;
import com.movies.model.Movie;

public class MovieAssessmentsDto {

	private float avgAssessments;
	private Page<AssessmentDto> assessments;
	
	public MovieAssessmentsDto() {

	}
	
	public MovieAssessmentsDto(Movie movie,  Page<Assessment>assessments) {
		this.assessments = assessments.map(AssessmentDto::new);
	}

	public Page<AssessmentDto> getAssessments() {
		return assessments;
	}

	public float getAvgAssessments() {
		return avgAssessments;
	}

	public void setAvgAssessments(float avgAssessments) {
		this.avgAssessments = avgAssessments;
	}
}
