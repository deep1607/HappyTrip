package com.myapp.spring.login;

import java.util.Optional;

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
        Optional<Access> user = userRepository.findByEmail(email);
         
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + email));
         
        return user.map(AccessDetails::new).get();
    }
 
}