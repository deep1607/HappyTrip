package com.myapp.spring.admin.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.spring.admin.model.Airlines;
import com.myapp.spring.admin.repository.AirlineRepository;

@Service
public class AirlineService {

	@Autowired
	private AirlineRepository repository;
	
	public List<Airlines> saveall(List<Airlines> airline) {
		
		return repository.saveAll(airline);
	}
	
	public Airlines updateAirline(Airlines airline) {
		
		String id = airline.getAirlineCode();
		
		Airlines oldAirline = null;
		if(repository.findByAirlineCode(id).isPresent()) {
			oldAirline=repository.findByAirlineCode(id).orElseGet(Airlines::new);
		}
		BeanUtils.copyProperties(airline, oldAirline);
		
		return repository.save(oldAirline);
	}
	
	
}
