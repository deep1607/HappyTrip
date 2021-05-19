package com.myapp.spring.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
 
public class AccessDetailsService implements UserDetailsService {
 
    @Autowired
    private AccessRepository userRepository;
     
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        Access user = userRepository.findByEmail(email).get();
         
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return new AccessDetails(user);
    }
 
}