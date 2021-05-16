package com.myapp.spring.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.myapp.spring.model.Admin;


@Repository
public interface AdminRepository extends JpaRepository < Admin, String > {
	
	Optional<Admin> findByemailAndPassword(String email,String password);
	
   
}

