package com.mertakl.springboot.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mertakl.springboot.flight.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

}
