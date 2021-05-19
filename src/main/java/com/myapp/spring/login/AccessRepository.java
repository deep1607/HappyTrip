package com.myapp.spring.login;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AccessRepository extends CrudRepository<Access, Integer> {
 
    
	Optional <Access> findByEmail(String email);
}