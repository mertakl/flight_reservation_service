package com.mertakl.springboot.flight.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.mertakl.springboot.flight.audit.UserDateAudit;

@Entity
@Table(name = "flights")
public class Flight extends UserDateAudit {

	@NotBlank
	@Size(max = 50)
	private String flightNumber;

	@OneToOne(cascade=CascadeType.PERSIST)
	private Aircraft operatingAircraft;

	@OneToOne(cascade=CascadeType.PERSIST)
	private City departureCity;

	@OneToOne(cascade=CascadeType.PERSIST)
	private City arrivalCity;

	private Date dateOfDeparture;

	private Timestamp estimatedDuration;
	
	public Flight() {
		
	}

	public Flight(@NotBlank @Size(max = 50) String flightNumber, Aircraft operatingAircraft, City departureCity,
			City arrivalCity, Date dateOfDeparture, Timestamp estimatedDuration) {
		super();
		this.flightNumber = flightNumber;
		this.operatingAircraft = operatingAircraft;
		this.departureCity = departureCity;
		this.arrivalCity = arrivalCity;
		this.dateOfDeparture = dateOfDeparture;
		this.estimatedDuration = estimatedDuration;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public Aircraft getOperatingAircraft() {
		return operatingAircraft;
	}

	public void setOperatingAircraft(Aircraft operatingAircraft) {
		this.operatingAircraft = operatingAircraft;
	}

	public City getDepartureCity() {
		return departureCity;
	}

	public void setDepartureCity(City departureCity) {
		this.departureCity = departureCity;
	}

	public City getArrivalCity() {
		return arrivalCity;
	}

	public void setArrivalCity(City arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

	public Date getDateOfDeparture() {
		return dateOfDeparture;
	}

	public void setDateOfDeparture(Date dateOfDeparture) {
		this.dateOfDeparture = dateOfDeparture;
	}

	public Timestamp getEstimatedDuration() {
		return estimatedDuration;
	}

	public void setEstimatedDuration(Timestamp estimatedDuration) {
		this.estimatedDuration = estimatedDuration;
	}

	@Override
	public String toString() {
		return "Flight [flightNumber=" + flightNumber + ", operatingAircraft=" + operatingAircraft + ", departureCity="
				+ departureCity + ", arrivalCity=" + arrivalCity + ", dateOfDeparture=" + dateOfDeparture
				+ ", estimatedDuration=" + estimatedDuration + "]";
	}

}
