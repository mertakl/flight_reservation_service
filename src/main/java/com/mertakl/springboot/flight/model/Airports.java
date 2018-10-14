package com.mertakl.springboot.flight.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.mertakl.springboot.flight.audit.DateAudit;

@Entity
@Table(name = "airports")
public class Airports extends DateAudit{
	
	@NotBlank
	@Size(max = 50)
	private String airportName;
	
	@NotBlank
	@Size(max = 10)
	private String airportCode;
	
	@OneToOne
	private City city;
	
	public Airports() {
		
	}

	public Airports(@NotBlank @Size(max = 50) String airportName, @NotBlank @Size(max = 10) String airportCode,
			City city) {
		super();
		this.airportName = airportName;
		this.airportCode = airportCode;
		this.city = city;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Airports [airportName=" + airportName + ", airportCode=" + airportCode + ", airportCity="
				+ city + "]";
	}
	
	

}
