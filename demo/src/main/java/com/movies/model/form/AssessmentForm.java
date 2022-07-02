package com.movies.model.form;

import javax.validation.constraints.NotNull;
public class AssessmentForm {

	@NotNull
	private String assessment;

	public String getAssessment() {
		return assessment;
	}
}
