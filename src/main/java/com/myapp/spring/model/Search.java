package com.myapp.spring.model;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Airlines")
public class Search {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "FLIGHT_NO")
	private Integer flightNo;
	
	@Column(name = "AIRLINE_NAME",nullable = false)
	private String airlineName;
	
	@Column(name = "FROM_CITY")
	private String fromCity;
	
	@Column(name = "TO_CITY")
	private String toCity;
	
	@Column(name = "DATE")
	private Date date;
	
	@Column(name = "DEP_TIME")
	private  Time depTime;
	
	@Column(name = "ARR_TIME")
	private  Time arrTime;
	
	@Column(name = "COST")
	private Double cost;
	
	@Column(name = "TOTAL_SEATS")
	private Integer totalSeats;
	
	@Column(name = "AVL_SEATS")
	private Integer avlSeats;

	public Integer getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(Integer flightNo) {
		this.flightNo = flightNo;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getDepTime() {
		return depTime;
	}

	public void setDepTime(Time depTime) {
		this.depTime = depTime;
	}

	public Time getArrTime() {
		return arrTime;
	}

	public void setArrTime(Time arrTime) {
		this.arrTime = arrTime;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Integer getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(Integer totalSeats) {
		this.totalSeats = totalSeats;
	}

	public Integer getAvlSeats() {
		return avlSeats;
	}

	public void setAvlSeats(Integer avlSeats) {
		this.avlSeats = avlSeats;
	}

	public Search(Integer flightNo, String airlineName, String fromCity, String toCity, Date date, Time depTime,
			Time arrTime, Double cost, Integer totalSeats, Integer avlSeats) {
		this.flightNo = flightNo;
		this.airlineName = airlineName;
		this.fromCity = fromCity;
		this.toCity = toCity;
		this.date = date;
		this.depTime = depTime;
		this.arrTime = arrTime;
		this.cost = cost;
		this.totalSeats = totalSeats;
		this.avlSeats = avlSeats;
	}

	@Override
	public int hashCode() {
		return Objects.hash(airlineName, arrTime, avlSeats, cost, date, depTime, flightNo, fromCity, toCity,
				totalSeats);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Search))
			return false;
		Search other = (Search) obj;
		return Objects.equals(airlineName, other.airlineName) && Objects.equals(arrTime, other.arrTime)
				&& Objects.equals(avlSeats, other.avlSeats) && Objects.equals(cost, other.cost)
				&& Objects.equals(date, other.date) && Objects.equals(depTime, other.depTime)
				&& Objects.equals(flightNo, other.flightNo) && Objects.equals(fromCity, other.fromCity)
				&& Objects.equals(toCity, other.toCity) && Objects.equals(totalSeats, other.totalSeats);
	}
	
	
	
	
	

	
	
}
