package com.myapp.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.spring.model.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login , Integer>{
	
	
	

}

