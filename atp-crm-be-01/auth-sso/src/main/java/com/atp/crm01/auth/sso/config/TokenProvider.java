package com.atp.crm01.auth.sso.config;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SecurityException;
import io.jsonwebtoken.security.SignatureException;

@Component
@Slf4j
public class TokenProvider {
	@Value("${sec.jwt.access_token.secret}")
	private String secretKey;
	
	@Value("${sec.jwt.access_token.expiry}")
	long atExpiry;
	
	
	private Key keys() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
	}
	
	public String generateToken(String email, Map<String, String> claims) {
		return Jwts.builder()
				.setSubject(email)
				.setClaims(claims)
				.setIssuedAt(new Date())
				.setExpiration(Date.from(Instant.now().plusMillis(atExpiry)))
				.signWith(keys(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	public String getSubjectFromToken(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(keys())
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().build()
				.setSigningKey(keys())
				.parseClaimsJws(token);
			return true;
		} catch (ExpiredJwtException e) {
			// TODO: handle exception
			log.error("------------------------ {JWT Error }  - ExpiredJwtException :: "+ e.getMessage());
			log.error("------------------------ Expired at :: "+ e.getClaims().getExpiration()+" ------------------------ ");
		}
		catch (UnsupportedJwtException e) {
			// TODO: handle exception
			log.error("------------------------ {JWT Error }  - UnsupportedJwtException :: "+ e.getMessage());
		}
		catch (MalformedJwtException e) {
			// TODO: handle exception
			log.error("------------------------ {JWT Error }  - MalformedJwtException :: "+ e.getMessage());
		}
		catch (SignatureException e) {
			// TODO: handle exception
			log.error("------------------------ {JWT Error }  - SignatureException :: "+ e.getMessage());
		}catch (SecurityException e) {
			// TODO: handle exception
			log.error("------------------------ {JWT Error }  - SecurityException :: "+ e.getMessage());
		}
		catch (IllegalArgumentException e) {
			// TODO: handle exception
			log.error("------------------------ {JWT Error }  - IllegalArgumentException :: "+ e.getMessage());
		}
		return false;
	}

}
