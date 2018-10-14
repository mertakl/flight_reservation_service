package com.mertakl.springboot.flight.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.mertakl.springboot.flight.audit.UserDateAudit;

@Entity
@Table(name = "passenger")
public class Passenger extends UserDateAudit {

	@NotBlank
	@Size(max = 50)
	private String firstName;
	@NotBlank
	@Size(max = 50)
	private String lastName;
	private Long identityNumber;
	@NotBlank
	@Size(max = 100)
	private String passportNumber;
	@OneToOne(cascade = CascadeType.PERSIST)
	private Country country;

	public Passenger() {

	}

	public Passenger(@NotBlank @Size(max = 50) String firstName, @NotBlank @Size(max = 50) String lastName,
			@NotBlank @Size(max = 100) Long identityNumber, @NotBlank @Size(max = 100) String passportNumber,
			Country country) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.identityNumber = identityNumber;
		this.passportNumber = passportNumber;
		this.country = country;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(Long identityNumber) {
		this.identityNumber = identityNumber;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Passenger [firstName=" + firstName + ", lastName=" + lastName + ", identityNumber=" + identityNumber
				+ ", passportNumber=" + passportNumber + ", countryOfOrigin=" + country + "]";
	}

}
