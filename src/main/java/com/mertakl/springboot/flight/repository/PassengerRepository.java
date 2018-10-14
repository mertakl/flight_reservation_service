package com.mertakl.springboot.flight.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mertakl.springboot.flight.model.City;
import com.mertakl.springboot.flight.model.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

	Page<City> findByCountryId(Long countryId, Pageable pageable);

}
