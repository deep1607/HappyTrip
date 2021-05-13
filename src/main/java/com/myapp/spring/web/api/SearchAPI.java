package com.myapp.spring.web.api;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.myapp.spring.model.Search;
import com.myapp.spring.repository.SearchRepository;

@RestController
@RequestMapping("/user/search")
public class SearchAPI {
	@Autowired
	private SearchRepository repository;
		 @GetMapping("/flight/{fromCity}/{toCity}/{date}")
		    public ResponseEntity<List<Search>> findFlightByCitiesAndDate
		    (@PathVariable String fromCity,
		    @PathVariable String toCity,
		    @PathVariable Date date){
			return new ResponseEntity<List<Search>>(
			repository.findByFromCityAndToCityAndDateOrderByCostAsc(fromCity,toCity,date).get(),HttpStatus.OK);}   
}

	


