package com.mertakl.springboot.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mertakl.springboot.flight.model.AirlineCompany;

public interface AirlineCompanyRepository extends JpaRepository<AirlineCompany, Long>{

}
