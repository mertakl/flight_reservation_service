package com.mertakl.springboot.flight.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.mertakl.springboot.flight.audit.DateAudit;

@Entity
@Table(name = "airline_company")
public class AirlineCompany extends DateAudit {

	@NotBlank
	@Size(max = 50)
	private String companyName;

	private String shortName;

	public AirlineCompany() {

	}

	public AirlineCompany(@NotBlank @Size(max = 50) String companyName, String shortName) {
		super();
		this.companyName = companyName;
		this.shortName = shortName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Override
	public String toString() {
		return "AirlineCompany [companyName=" + companyName + ", shortName=" + shortName + "]";
	}

}
