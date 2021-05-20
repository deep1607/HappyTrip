package com.myapp.spring.admin.web.api; 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.spring.admin.model.City;
import com.myapp.spring.admin.repository.CityRepository;
import com.myapp.spring.admin.service.CityService;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@RestController
@RequestMapping("/admin/cities")
public class CityApi {
	@Autowired
	private CityRepository repository;
	
	@Autowired
	private CityService service;
	//Admin can add 1 new city
	
	@PostMapping
	public ResponseEntity<City> saveNewCity(@RequestBody City city){

	return new ResponseEntity<>(repository.save(city),HttpStatus.CREATED);
	
	}
	
	//Admin can add multiple new city
	
	@PostMapping("/bulk")
	public ResponseEntity<List<City>> bulkProuctsInsert(@RequestBody List<City> city){
	
		
	
	return new ResponseEntity<>(service.saveall(city),HttpStatus.CREATED);
	}
	
	
	//Admin Search all cities displayed 
	
	@GetMapping
	public ResponseEntity<List<City>> viewall(){
		return new ResponseEntity<>(repository.viewcity(),HttpStatus.OK);	
	}
	
	
	//Admin can find by city id
	
	@GetMapping("findCity/id:{id}")
	public ResponseEntity<City> findByCityId(@PathVariable("id") String id){
		
		City city = null;
		if(repository.findBycityId(id).isPresent()) {
			city=repository.findBycityId(id).orElseGet(City::new);
		}
		return new ResponseEntity<>(city,HttpStatus.OK);
	}
	
	
	//Admin can find by city name
	
	@GetMapping("findCity/city:{name}")
	public ResponseEntity<City> findByCityName(@PathVariable("name") String name){
		
		City city = null;
		if(repository.findBycityName(name).isPresent()) {
			city=repository.findBycityName(name).orElseGet(City::new);
		}
		
		return new ResponseEntity<>(city,HttpStatus.OK);
	}
	
	//Admin can Update City Name
	
		@PutMapping("update")
		public ResponseEntity<City> updatecitybyid(
				@RequestBody City city){
		
		return new ResponseEntity<>(service.updateCity(city),HttpStatus.CREATED);
		}
		
		
}
