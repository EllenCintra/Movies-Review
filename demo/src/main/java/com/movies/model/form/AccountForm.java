package com.movies.model.form;

import javax.validation.constraints.NotBlank;

public class AccountForm {

	@NotBlank
	private String name;
	
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;

	public AccountForm(String name, String username, String password) {
		this.name = name;
		this.username = username;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}
}
