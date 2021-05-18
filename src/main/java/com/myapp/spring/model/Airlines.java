package com.myapp.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="Airlines")

public class Airlines {
	
	
	//@GeneratedValue(strategy = GenerationType.AUTO)
	//@Id
	@Column(name="Airline_code",nullable=false,unique = true)
	private String Airline_code;
	
	@Column(name="Airline_name",nullable=false,unique = true)
	private String Airline_name;

//	@Column(name="Airline_logo",nullable=false,unique = true)
//	private String Airline_logo;

	
	
	public Airlines(String airline_code, String airline_name) {
		Airline_code = airline_code;
		Airline_name = airline_name;
//		Airline_logo = airline_logo;
	}

	public Airlines() {
		// TODO Auto-generated constructor stub
	}

	public String getAirline_code() {
		return Airline_code;
	}

	public void setAirline_code(String airline_code) {
		Airline_code = airline_code;
	}

	public String getAirline_name() {
		return Airline_name;
	}

	public void setAirline_name(String airline_name) {
		Airline_name = airline_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Airline_code == null) ? 0 : Airline_code.hashCode());
		result = prime * result + ((Airline_name == null) ? 0 : Airline_name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Airlines other = (Airlines) obj;
		if (Airline_code == null) {
			if (other.Airline_code != null)
				return false;
		} else if (!Airline_code.equals(other.Airline_code))
			return false;
		if (Airline_name == null) {
			if (other.Airline_name != null)
				return false;
		} else if (!Airline_name.equals(other.Airline_name))
			return false;
		return true;
	}

//	public String getAirline_logo() {
//		return Airline_logo;
//	}
//
//	public void setAirline_logo(String airline_logo) {
//		Airline_logo = airline_logo;
//	}




}
