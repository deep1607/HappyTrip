package com.myapp.spring.login;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
 

public class AccessDetails implements UserDetails {
 
	private String userName;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;
    
	
  public AccessDetails(Access access) {
	this.userName = access.getEmail();
    this.password = access.getPassword();
    this.active = access.isActive();
    this.authorities = Arrays.stream(access.getRole().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
	}


@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
}
 
    @Override
    public String getPassword() {
        return password;
    }
 
    @Override
    public String getUsername() {
        return userName;
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return active;
    }
 
}