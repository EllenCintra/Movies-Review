package com.movies.model.dto;

import java.time.LocalDateTime;

import com.movies.model.Assessment;
import com.movies.model.mapper.AccountMapper;

public class AssessmentDto {

	private Long id;
	private AccountIdNameDto user;
	private Integer assessment;
	private LocalDateTime createdAt = LocalDateTime.now();
	
	public AssessmentDto(Assessment assessment) {
		this.id = assessment.getId();
		this.user = AccountMapper.toDto(assessment.getUser());
		this.assessment = assessment.getAssessment();
		this.createdAt = assessment.getCreatedAt();
	}
	
	public Long getId() {
		return id;
	}
	
	public AccountIdNameDto getUser() {
		return user;
	}
	
	public Integer getAssessment() {
		return assessment;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	
}
