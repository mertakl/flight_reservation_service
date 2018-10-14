package com.mertakl.springboot.flight.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.mertakl.springboot.flight.audit.DateAudit;

@Entity
@Table(name = "country")
public class Country extends DateAudit{

	@NotBlank
	@Size(max = 100)
	@Column(unique = true)
	private String countryName;
	
	public Country() {
		
	}

	public Country(@NotBlank @Size(max = 100) String countryName) {
		super();
		this.countryName = countryName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@Override
	public String toString() {
		return "Country [countryName=" + countryName + "]";
	}

}
