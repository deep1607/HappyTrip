package com.myapp.spring.login;

import javax.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
 
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
	
	@Autowired
	UserDetailsService userDetailsService;
//	
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new AccessDetailsService();
//    }
     
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
	@Bean
    public PasswordEncoder getPasswordEncoder() {
        //return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }
     
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService());
//        authProvider.setPasswordEncoder(passwordEncoder());
//         
//        return authProvider;
//    }
//    
   
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.authenticationProvider(authenticationProvider());
        auth.userDetailsService(userDetailsService);
       // auth.authenticationProvider(authenticationProvider());
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	http.csrf().disable();
    	http
        	.authorizeRequests()
        	.antMatchers("/admin/**").hasRole("ADMIN")
        	.antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
        	.antMatchers("/**").permitAll()
        	.and()
    		.formLogin();
//    		.and()
//    		.csrf().disable();
//    	http.authorizeRequests()
//        .antMatchers("/admin/**")
//    	.hasRole("ADMIN")
//        .antMatchers("/user/**")
//            .hasAnyRole("ADMIN", "USER")
//
//        	.antMatchers("/login","/**")
//            .permitAll()
//        .and()
//            .formLogin()
//           
//            .defaultSuccessUrl("/admin/cities")
//            .failureUrl("/login?error=true")
//            .permitAll()
//        .and()
//            .logout()
//            .logoutSuccessUrl("/login?logout=true")
//            .invalidateHttpSession(true)
//            .permitAll()
//        .and()
//            .csrf()
//            .disable();        
        
//    	http .csrf().disable()
//        .authorizeRequests()
//        .antMatchers("/**").permitAll()
//        .anyRequest().authenticated()
//        .and().formLogin();
        
        		     
    }
}