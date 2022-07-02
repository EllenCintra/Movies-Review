package com.movies.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.movies.model.enums.Profile;


@Entity
@Table(name="Accounts")
public class Account implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(unique = true)
	private String username;

	private String password;

	private int points= 0;

	private Profile profile = Profile.Reader;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<AuthProfile> authProfiles = new ArrayList<>();

	public Account() {
	} 

	public Account(String name, String username, String password) {
		this.name = name;
		this.username = username;
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return (Collection<? extends GrantedAuthority>) this.authProfiles;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getPoints() {
		return points;
	}

	public void addPoint() {
		points += 1;
		
		if (profile != Profile.Moderator) {
			switch (points) {
			case 20:
				profile = Profile.Basic;
				break;
			case 100:
				profile = Profile.Advanced;
				break;
			case 1000:
				profile = Profile.Moderator;
			}
		}
	}

	public Profile getProfile() {
		return profile;
	}

	public void setToModeratorProfile() {
		profile = Profile.Moderator;
	}
}


