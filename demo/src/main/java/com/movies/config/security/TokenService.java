package com.movies.config.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.movies.model.Account;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${jwt.expiration}")
	private String expiration;
	
	@Value("${jwt.secret}")
	private String secret;

	public String createToken(Authentication authentication) {
		Account logged = (Account) authentication.getPrincipal();
		Date createdAt = new Date();
		Date expiresIn = new Date(createdAt.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder()
				.setIssuer("Movies Review API")
				.setSubject(logged.getId().toString())
				.setIssuedAt(createdAt)
				.setExpiration(expiresIn)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}
	
	public boolean isValidToken(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public String getToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		//Removing "Bearer " 
		return token.substring(7, token.length());
	}

	public Long getIdAccount(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
