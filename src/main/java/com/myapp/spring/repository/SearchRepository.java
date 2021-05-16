package com.myapp.spring.repository;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.myapp.spring.model.Search;

//Annotation is to identify that this is spring managed bean
//This is a data repository class

@Repository
public interface SearchRepository extends JpaRepository<Search, Integer> {

		
		//Optional<Search> findByfromcityAndtocityAnddate(String fromcity,String tocity,  Date date);
		
		@Query(value="select * from Airlines where FROM_CITY = ? and TO_CITY = ? and DATE = ? and AVL_SEATS > ?",nativeQuery=true)
		public Optional<Search> checkingSeatAvail(String fromcity,String tocity,  Date date, int seats);
		
	
		//Optional <Search> findByfromcityAndtocityAndDateAndavlSeatsGreaterThan(String fromcity,String tocity,  Date date, int avlSeats);
	}
	







