package com.movies.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class AuthProfile implements GrantedAuthority {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	public Long getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public void setname (String name) {
		this.name = name;
	}

	@Override
	public String getAuthority() {
		return name;
	}
	
}
