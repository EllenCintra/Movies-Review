package com.movies.model.dto;

public class AccountIdNameDto {
	
	private Long id;
	private String name;
	 
	public AccountIdNameDto(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
