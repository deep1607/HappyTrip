package com.myapp.spring.admin.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.spring.admin.model.City;
import com.myapp.spring.admin.repository.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository repository;
	
	public List<City> saveall(List<City> city) {
		
		return repository.saveAll(city);
	}
	
	public City updateCity(City city) {
		
		String id = city.getCityId();
		
		
		City oldCity = null;
		if(repository.findBycityId(id).isPresent()) {
			oldCity=repository.findBycityId(id).orElseGet(City::new);
		}
		
		BeanUtils.copyProperties(city, oldCity);
		
		return repository.save(oldCity);
	}
	
	
}
