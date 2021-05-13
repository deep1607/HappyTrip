package com.myapp.spring.web.api;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.spring.model.City;
import com.myapp.spring.repository.CityRepository;

@SpringBootTest
@AutoConfigureMockMvc

public class CityApiTest {

	@MockBean
	private CityRepository repository;
	
	@Autowired
	private MockMvc mockMvc;
	

	@Test
	@DisplayName("Test Add City")
	public void testAddNewCity() throws Exception{
		
		City newcity=new City("MUM","Mumbai");
		City mockcity=new City("MUM","Mumbai");
		
		doReturn(mockcity).when(repository).save(ArgumentMatchers.any());
		
		mockMvc.perform(post("/admin/cities")
				// Validate Status should be 200 ok and json response recived
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new ObjectMapper().writeValueAsString(newcity)))
				
				//Validate Response body
				
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.cityId",is("MUM")))
				.andExpect(jsonPath("$.cityName",is("Mumbai")));
	}
		
	
}
