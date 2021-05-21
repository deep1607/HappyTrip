package com.myapp.spring.login;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AccessDetailsService implements UserDetailsService {
 
    @Autowired
    private AccessRepository userRepository;
     
    @Override
    public UserDetails loadUserByUsername(String email)
    {

    	
    	Access user = null;
    	if(userRepository.findByEmail(email).isPresent()) {
    		user=userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Not found: " + email));
    	}
          
        return new AccessDetails(user);
    }
 
}
