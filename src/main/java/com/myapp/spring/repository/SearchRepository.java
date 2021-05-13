package com.myapp.spring.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.myapp.spring.model.Search;

@Repository
public interface SearchRepository extends JpaRepository<Search, Integer> {

	Optional<List<Search>> findByFromCityAndToCityAndDateOrderByCostAsc(String fromCity,String toCity,Date date );

}






