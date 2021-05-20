package com.myapp.spring.admin.web.api; 

import java.util.List;

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

import com.myapp.spring.admin.model.Airlines;
import com.myapp.spring.admin.repository.AirlineRepository;
import com.myapp.spring.admin.service.AirlineService;

@RestController
@RequestMapping("/admin/airlines")
public class AirlineApi {

	//Dependency Injection
	@Autowired
	private AirlineRepository repository;

	@Autowired
	private AirlineService service;

	@PostMapping
	public ResponseEntity<Airlines> saveNewAirline(@RequestBody Airlines airline){

	return new ResponseEntity<>(repository.save(airline),HttpStatus.CREATED);
	
	}
	@PostMapping("/bulk")
	public ResponseEntity<List<Airlines>> bulkProuctsInsert(@RequestBody List<Airlines> airline){

	return new ResponseEntity<>(service.saveall(airline),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Airlines>> viewall(){
		
		return new ResponseEntity<>(repository.viewairline(),HttpStatus.OK);	
	}
	
	@GetMapping("findAirline/code:{id}")
	public ResponseEntity<Airlines> findByAirlineCode(@PathVariable("id") String id){
	
		Airlines airline = null;
		if(repository.findByAirlineCode(id).isPresent()) {
			airline=repository.findByAirlineCode(id).orElseGet(Airlines::new);
		}
		
		return new ResponseEntity<>(airline,HttpStatus.OK);
	}
	
	
	
	
	
	@GetMapping("findAirline/airline:{name}")
	public ResponseEntity<Airlines> findByAirlineName(@PathVariable("name") String name){
		
		Airlines airline = null;
		if(repository.findByAirlineName(name).isPresent()) {
			airline=repository.findByAirlineName(name).orElseGet(Airlines::new);
		}
					
		return new ResponseEntity<>(airline,HttpStatus.OK);
	}
	@PutMapping("update")
	public ResponseEntity<Airlines> updateAirlinebyid(
			@RequestBody Airlines airline){
	
	return new ResponseEntity<>(service.updateAirline(airline),HttpStatus.CREATED);
	}
	
}
