package com.mertakl.springboot.flight.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mertakl.springboot.flight.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	Page<Reservation> findByPassengerId(Long passengerId, Pageable pageable);

	Page<Reservation> findByFlightId(Long flightId, Pageable pageable);

}
