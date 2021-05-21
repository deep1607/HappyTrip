package com.myapp.spring.user.web.api;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.spring.user.model.Search;
import com.myapp.spring.user.repository.SearchRepository;

@RestController
@RequestMapping("")
public class SearchAPI {
	@Autowired
	private SearchRepository repository;
		 @GetMapping("/user/search/flight/{fromCity}/{toCity}/{date}")
		    public ResponseEntity<List<Search>> findFlightByRegisteredUserWithCitiesAndDate
		    (@PathVariable String fromCity,
		    @PathVariable String toCity,
		    @PathVariable Date date){
			
			 List<Search> search = new ArrayList<>();
			 List<Search> empty = new ArrayList<>();
			 
				if(repository.findByFromCityAndToCityAndDateOrderByCostAsc(fromCity,toCity,date).isPresent()) {
					search=repository.findByFromCityAndToCityAndDateOrderByCostAsc(fromCity,toCity,date).orElse(empty);
				}
			 return new ResponseEntity<>(search,HttpStatus.OK);
			 }
		 
		 @GetMapping("/search/flight/{fromCity}/{toCity}/{date}")
		    public ResponseEntity<List<Search>> findFlightByUnRegisteredUserWithCitiesAndDate
		    (@PathVariable String fromCity,
		    @PathVariable String toCity,
		    @PathVariable Date date){
			 
			 List<Search> empty = new ArrayList<>();
			
			 List<Search> search = new ArrayList<>();
				if(repository.findByFromCityAndToCityAndDateOrderByCostAsc(fromCity,toCity,date).isPresent()) {
					search=repository.findByFromCityAndToCityAndDateOrderByCostAsc(fromCity,toCity,date).orElse(empty);
				}
			 return new ResponseEntity<>(search,HttpStatus.OK);
		 }
}
