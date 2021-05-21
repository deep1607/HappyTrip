package com.myapp.spring.login;

public class LoginInfo {
    
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
	
    
    
    public LoginInfo(int id, String email, String password, String firstName, String lastName) {
		this.id = id;
		this.lastName = lastName;
		this.password = password;
		this.firstName = firstName;
		
		this.email = email;
	}
    
	public int getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
    
   
	
      
}