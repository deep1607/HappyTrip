package com.myapp.spring.web.api; 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.spring.model.City;
import com.myapp.spring.repository.CityRepository;

@RestController
@RequestMapping("/admin/cities")
public class CityApi {
	@Autowired
	private CityRepository repository;
	
	//Admin can add 1 new city
	//http://localhost:8888/admin/cities
	@PostMapping
	public ResponseEntity<City> saveNewCity(@RequestBody City city){

	return new ResponseEntity<City>(repository.save(city),HttpStatus.CREATED);
	}
	
	//Admin can add multiple new city
	//http://localhost:8888/admin/cities/bulk
	@PostMapping("/bulk")
	public ResponseEntity<List<City>> bulkProuctsInsert(@RequestBody List<City> city){
	
	return new ResponseEntity<List<City>>(repository.saveAll(city),HttpStatus.CREATED);
	}
	
	

	

	
	
}
