package com.myapp.spring.web.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.myapp.spring.model.Search;
import com.myapp.spring.repository.SearchRepository;


//This is a class which exposes Rest API's 
@RestController
@RequestMapping("/user/search")
public class SearchAPI {

	//Dependency Injection
	@Autowired
	private SearchRepository repository;
	
	//http://localhost:8989/api/s1/search
	@GetMapping
	public ResponseEntity<List<Search>> findAll(){
		
	return new ResponseEntity<List<Search>>(repository.findAll(),HttpStatus.OK);
	}
	
	@PostMapping("/insertFlight")
	public ResponseEntity<List<Search>> flightInsert(@RequestBody List<Search> flights){
		
		return new ResponseEntity<List<Search>>(repository.saveAll(flights), 
				HttpStatus.CREATED);
		}
//	
//	@GetMapping("/find/{cost}")
//    public ResponseEntity<List<Search>> findFlightBycost(
//            @PathVariable Double cost){
//        return new ResponseEntity<List<Search>>(
//                repository.findByCostLessThanOrEqual(cost).get(),HttpStatus.OK);
//    }
	
}
