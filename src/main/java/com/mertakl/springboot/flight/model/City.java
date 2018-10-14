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

import com.mertakl.springboot.flight.audit.DateAudit;

@Entity
@Table(name = "cities")
public class City extends DateAudit {

	@NotBlank
	@Size(max = 100)
	private String cityName;

	@NotBlank
	@Size(max = 10)
	private String cityCode;

	@OneToOne(cascade=CascadeType.PERSIST)
	Country country;
	
	public City() {
		
	}

	public City(@NotBlank @Size(max = 100) String cityName, @NotBlank @Size(max = 10) String cityCode,
			Country country) {
		super();
		this.cityName = cityName;
		this.cityCode = cityCode;
		this.country = country;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}
