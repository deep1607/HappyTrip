package com.myapp.spring.admin.web.api; 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.spring.admin.model.RouteModel;
import com.myapp.spring.admin.repository.RouteRepository;
import com.myapp.spring.admin.service.RouteService;

@RestController
@RequestMapping("/admin/route")
public class RouteApi {
	@Autowired
	private RouteRepository repository;
	
	@Autowired
	private RouteService service;
	//Admin can add 1 new route
	
	@PostMapping
	public ResponseEntity<RouteModel> saveNewRoute(@RequestBody RouteModel route){

	return new ResponseEntity<>(repository.save(route),HttpStatus.CREATED);
	
	}
	
	//Admin can add multiple new route

	@PostMapping("/bulk")
	public ResponseEntity<List<RouteModel>> saveListRoute(@RequestBody List<RouteModel> route){
	
		
	
	return new ResponseEntity<>(service.saveall(route),HttpStatus.CREATED);
	}
	

	//Admin Search all route displayed 
	
		@GetMapping
		public ResponseEntity<List<RouteModel>> viewall(){
			return new ResponseEntity<>(repository.viewAllRoute(),HttpStatus.OK);	
		}
	
	
	//Admin can Update route 
		
		@PutMapping("update")
		public ResponseEntity<RouteModel> updatecitybyid(
				@RequestBody RouteModel city){
		
		return new ResponseEntity<>(service.updateCity(city),HttpStatus.CREATED);
		}
		
		
}
