package com.example.kanbamtable.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class TokenUtil {
	
	private final static String ACCES_TOKEN_SECRET="4qhqBLrEBfYcaRHxhdb9zURb2rf8e7Ud";
	private final static long ACCES_TOKEN_VALIDITY_SECONDS= 2_592_000L;
	
	
	
	public static String createToken(String cellphone, String username, String role) {
		
		long expirationDateTime= ACCES_TOKEN_VALIDITY_SECONDS*1000;
		Date expirationDate= new Date(System.currentTimeMillis()+expirationDateTime);
		
		
		Map<String,Object> extra= new HashMap();
		extra.put("name",username);
		extra.put("role",role);
		
		return Jwts.builder()
		.setSubject(cellphone)
		.setExpiration(expirationDate)
		.addClaims(extra)
		.signWith(Keys.hmacShaKeyFor(ACCES_TOKEN_SECRET.getBytes()))
		.compact();
	}
	
	
	public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
		try {
				Claims claims=  Jwts.parserBuilder()
						.setSigningKey(ACCES_TOKEN_SECRET.getBytes())
						.build()
						.parseClaimsJws(token)
						.getBody(); 
				
				String cellphone = claims.getSubject();
				String role= (String) claims.get("role");
				
				
				return new UsernamePasswordAuthenticationToken(cellphone,null,Arrays.asList(new SimpleGrantedAuthority(role)));
		}catch(JwtException e) {
		
				return null;
		}
	}

}
