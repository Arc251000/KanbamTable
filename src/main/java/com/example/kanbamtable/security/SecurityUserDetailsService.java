package com.example.kanbamtable.security;

import com.example.kanbamtable.repository.UserAccountJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

	@Autowired
	UserAccountJPARepository ur;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		try {
			return new SecurityUser(ur.findByEmail(email).get());
		
		}catch(Exception e) {
		 throw new UsernameNotFoundException("No se ha encontrado al usuario");
		}
		
		
		
	}

}
