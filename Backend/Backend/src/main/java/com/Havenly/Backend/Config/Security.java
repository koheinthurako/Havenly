package com.Havenly.Backend.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Security {
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	 @Bean
	    public JavaMailSender javaMailSender() {
	        return new JavaMailSenderImpl();
	    }
	
	 
	 
	
	 
//	 @SuppressWarnings("deprecation")
//	@Bean
//	 public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
//	 http.authorizeRequests()
//	  .antMatchers ("/forgotPassword"). permitA11()
//	 .anyRequest().authenticated()
//	 .formLogin()
//	 .loginPage("/login" ).defaultSuccessUrl(" /userDashboard")
//	 .permitAll()
//	 .and()
//	 .logout()
//	 .permitAll();
//	 
//	 http.csrf().disable();
//	 http.headers().defaultsDisabled().cacheControl();
//	 return http.build();
//
//
//}
	 }
