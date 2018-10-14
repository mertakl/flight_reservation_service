package com.mertakl.springboot.flight.model;

import java.util.Date;

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
@Table(name = "aircraft")
public class Aircraft extends DateAudit {

	@NotBlank
	@Size(max = 50)
	private String aircraftName;

	private Date aircraftModel;

	private Long numberOfSeats;

	@OneToOne
	private AirlineCompany company;

	public Aircraft() {

	}

	public Aircraft(@NotBlank @Size(max = 50) String aircraftName, Date aircraftModel, Long numberOfSeats,
			AirlineCompany company) {
		super();
		this.aircraftName = aircraftName;
		this.aircraftModel = aircraftModel;
		this.numberOfSeats = numberOfSeats;
		this.company = company;
	}

	public String getAircraftName() {
		return aircraftName;
	}

	public void setAircraftName(String aircraftName) {
		this.aircraftName = aircraftName;
	}

	public Date getAircraftModel() {
		return aircraftModel;
	}

	public void setAircraftModel(Date aircraftModel) {
		this.aircraftModel = aircraftModel;
	}

	public Long getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(Long numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public AirlineCompany getCompany() {
		return company;
	}

	public void setCompanyName(AirlineCompany company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Aircraft [aircraftName=" + aircraftName + ", aircraftModel=" + aircraftModel + ", numberOfSeats="
				+ numberOfSeats + ", companyName=" + company + "]";
	}

}
