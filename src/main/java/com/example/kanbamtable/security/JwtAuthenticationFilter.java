package com.example.kanbamtable.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collections;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,HttpServletResponse response )throws AuthenticationException {
		
		AuthCredentials credentials= new AuthCredentials();		
		try {
			
			credentials= new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
		}catch(Exception e){
		}
		
		
		UsernamePasswordAuthenticationToken usernamePat= new UsernamePasswordAuthenticationToken(
				credentials.getEmail(),
				credentials.getPassword(),
				Collections.emptyList()
				);
		
		
		return getAuthenticationManager().authenticate(usernamePat);
		
		
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		
		SecurityUser userDetails= (SecurityUser) authResult.getPrincipal();
		SimpleGrantedAuthority authority = (SimpleGrantedAuthority) userDetails.getAuthorities().toArray()[0];
		String token = TokenUtil.createToken(userDetails.getUsername(),userDetails.getName(),authority.getAuthority());
		
		
		response.addHeader("Authorization", "Bearer "+token);
		response.getWriter().flush();
		
		
		super.successfulAuthentication(request, response, chain, authResult);
	}
	
	
	
	
	
}
