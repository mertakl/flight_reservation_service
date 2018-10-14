package com.mertakl.springboot.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mertakl.springboot.flight.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

}
