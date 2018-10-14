package com.mertakl.springboot.flight.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.mertakl.springboot.flight.audit.UserDateAudit;

@Entity
@Table(name = "reservation")
public class Reservation extends UserDateAudit{

	private Boolean checkedIn;

	private int numberOfBags;

	@ManyToOne
	private Passenger passenger;

	@ManyToOne
	private Flight flight;
	
	public Reservation() {
		
	}

	public Reservation(@NotBlank Boolean checkedIn, int numberOfBags, Passenger passenger, Flight flight) {
		super();
		this.checkedIn = checkedIn;
		this.numberOfBags = numberOfBags;
		this.passenger = passenger;
		this.flight = flight;
	}

	public Boolean getCheckedIn() {
		return checkedIn;
	}

	public void setCheckedIn(Boolean checkedIn) {
		this.checkedIn = checkedIn;
	}

	public int getNumberOfBags() {
		return numberOfBags;
	}

	public void setNumberOfBags(int numberOfBags) {
		this.numberOfBags = numberOfBags;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	@Override
	public String toString() {
		return "Reservation [checkedIn=" + checkedIn + ", numberOfBags=" + numberOfBags + ", passenger="
				+ passenger + ", flight=" + flight + "]";
	}

}
