package com.myapp.spring.web.api; 

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.spring.model.Airlines;
import com.myapp.spring.repository.AirlineRepository;

@RestController
@RequestMapping("/admin/airlines")
public class AirlineApi {

	//Dependency Injection
	@Autowired
	private AirlineRepository repository;

	//Admin is able to view all airlines
	//http://localhost:8888/admin/airlines/findAll
	@GetMapping("/findAll")
	public ResponseEntity<List<Airlines>> findAllAirlines(){
		return new ResponseEntity<List<Airlines>>(repository.findAll(),HttpStatus.OK);
		
	}
	
	//Admin is able to view all airlines
	//http://localhost:8888/admin/airlines/find/{Airline_name}
	@GetMapping("/find/{airline_name}")
	public ResponseEntity<Airlines> findAirlineByName(@PathVariable("airline_name") String airline_name){
		return new ResponseEntity<Airlines>(repository.findByAirline_name("airline_name").get(),HttpStatus.OK);
			
	}
	
	//Admin is able to view all airlines
	//http://localhost:8888/admin/airlines/find/{Airline_code}
	@GetMapping("/find/{airline_code}")
	public ResponseEntity<Airlines> findAirlinesByCode(@PathVariable("airline_code") String airline_code){
		return new ResponseEntity<Airlines>(repository.findByAirline_code("airline_code").get(),HttpStatus.OK);
			
	}
	
	//Admin should be able to add an airline
	//http://localhost:8888/admin/airlines/add
	@PostMapping("/add")
	public ResponseEntity<Airlines> addNewAirline(@RequestBody Airlines airline){
		return new ResponseEntity<Airlines>(repository.save(airline),HttpStatus.CREATED);
		
	}
	
	//Admin should be able to add an airline
	//http://localhost:8888/admin/airlines/add/bulk
	@PostMapping("/add/bulk")
	public ResponseEntity<List<Airlines>> bulkAirlineInsert(@RequestBody List<Airlines> airline){
		return new ResponseEntity<List<Airlines>>(repository.saveAll(airline),HttpStatus.CREATED);
			
	}
	
	//Admin should be able to update an airline
	//http://localhost:8888/admin/airlines/update/{airline_code}
	@PutMapping("/update/{airline_code}")
	public ResponseEntity<Airlines> updateAirlinesByCode(@PathVariable("airline_code") String airline_code,
			@RequestBody Airlines airline){
			
	Airlines existingAirline = repository.findByAirline_code(airline_code).get();	
			
	BeanUtils.copyProperties(airline, existingAirline);
			
	return new ResponseEntity<Airlines>(repository.save(existingAirline),
			HttpStatus.CREATED);
			
	}
	
	//Admin should be able to update an airline
	//http://localhost:8888/admin/airlines/update/{airline_name}
	@PutMapping("/update/{airline_name}")
	public ResponseEntity<Airlines> updateAirlinesByName(@PathVariable("airline_name") String airline_name,
			@RequestBody Airlines airline){
			
	Airlines existingAirline = repository.findByAirline_name(airline_name).get();	
			
	BeanUtils.copyProperties(airline, existingAirline);
			
	return new ResponseEntity<Airlines>(repository.save(existingAirline),
			HttpStatus.CREATED);
			
	}

	
	
}
