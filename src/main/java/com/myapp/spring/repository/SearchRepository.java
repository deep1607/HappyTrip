package com.myapp.spring.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.myapp.spring.model.Search;

//Annotation is to identify that this is spring managed bean
//This is a data repository class

@Repository
public interface SearchRepository extends JpaRepository<Search, Integer> {
		
	
	Optional<List<Search>> sortByPrice(Collection<Double> price);
	
	
}






