package com.example.kanbamtable.security;

import org.springframework.security.core.GrantedAuthority;

public class SecurityAuthorities implements GrantedAuthority {

	private String authority;
	
	
	
	public SecurityAuthorities(String authority) {
		super();
		this.authority = authority;
	}



	@Override
	public String getAuthority() {
		
		// TODO Auto-generated method stub
		return authority;
	}

}
