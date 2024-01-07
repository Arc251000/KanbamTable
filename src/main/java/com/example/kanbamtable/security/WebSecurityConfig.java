package com.example.kanbamtable.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	SecurityUserDetailsService us;
	
	@Autowired
	private JWTAuthorizationFilter jwtAuthorizationFilter;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	 public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
       
		JwtAuthenticationFilter jwtAuthenticationFilter= new JwtAuthenticationFilter();
		jwtAuthenticationFilter.setAuthenticationManager(authManager);
		jwtAuthenticationFilter.setFilterProcessesUrl("/login");
		
		http.cors(Customizer.withDefaults()).csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authorize -> authorize 
							                	.requestMatchers("/section/delete").hasAuthority("ROLE_ADMIN")
							                	.anyRequest().authenticated())
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilter(jwtAuthenticationFilter)
            .addFilterBefore(jwtAuthorizationFilter,JwtAuthenticationFilter.class);
        
        
        return http.build();
    }
	
	
	
	
	
	@Bean
	 AuthenticationManager authManager(HttpSecurity http) throws Exception {
		 
		
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				 .userDetailsService(us)
				 .passwordEncoder(passwordEncoder())
				 .and()
				 .build(); 
	 }
}