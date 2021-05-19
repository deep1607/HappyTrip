package com.myapp.spring.admin.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.spring.admin.model.RouteModel;
import com.myapp.spring.admin.repository.RouteRepository;

@Service
public class RouteService {

	@Autowired
	private RouteRepository repository;
	
	public List<RouteModel> saveall(List<RouteModel> route) {
		
		return repository.saveAll(route);
	}
	
	public RouteModel updateCity(RouteModel route) {
		
		String fromCity = route.getFromCity();

		String toCity = route.getToCity();

		RouteModel oldRoute = repository.findByFromCityAndToCity(fromCity, toCity).get();
		
		BeanUtils.copyProperties(route, oldRoute);

		return repository.save(oldRoute);
		}
		
	}
	
	

