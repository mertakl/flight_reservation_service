package com.mertakl.springboot.flight.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mertakl.springboot.flight.exception.ResourceNotFoundException;
import com.mertakl.springboot.flight.model.City;
import com.mertakl.springboot.flight.model.Passenger;
import com.mertakl.springboot.flight.model.Reservation;
import com.mertakl.springboot.flight.repository.FlightRepository;
import com.mertakl.springboot.flight.repository.PassengerRepository;
import com.mertakl.springboot.flight.repository.ReservationRepository;

@RestController
@RequestMapping("/api")
public class ReservationController {

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	FlightRepository flightRepository;

	@Autowired
	PassengerRepository passengerRepository;

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation getAllReservations
	 */
	@GetMapping("/reservations")
	public List<Reservation> getAllReservations() {
		return reservationRepository.findAll();
	}

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation
	 * getAllReservationsByPassenger
	 */
	@GetMapping("/passenger/{passengerId}/reservations")
	public Page<Reservation> getAllReservationsByPassenger(@PathVariable(value = "passengerId") Long passengerId,
			Pageable pageable) {
		return reservationRepository.findByPassengerId(passengerId, pageable);
	}

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation getAllReservationsByFlight
	 */
	@GetMapping("/flight/{flightId}/reservations")
	public Page<Reservation> getAllReservationsByFlight(@PathVariable(value = "flightId") Long flightId,
			Pageable pageable) {
		return reservationRepository.findByFlightId(flightId, pageable);
	}

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation createReservation
	 */
	@PostMapping("/reservation")
	public Reservation createReservation(@RequestParam(value = "passengerId") Long passengerId,
			@RequestParam(value = "flightId") Long flightId, @Valid @RequestBody Reservation reservation) {

		return flightRepository.findById(flightId).map(flight -> {
			Optional<Passenger> passenger = passengerRepository.findById(passengerId);
			reservation.setFlight(flight);
			reservation.setPassenger(passenger.get());
			return reservationRepository.save(reservation);
		}).orElseThrow(() -> new ResourceNotFoundException("FlightId", "not found", flightId));
	}

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation getReservationById
	 */
	@GetMapping("/reservation/{id}")
	public Reservation getReservationById(@PathVariable(value = "id") Long reservationId) {
		return reservationRepository.findById(reservationId)
				.orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", reservationId));
	}

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation updateReservation
	 */
	@PutMapping("/reservation")
	public Reservation updateReservation(@RequestParam(value = "reservationId") Long reservationId,
			@RequestParam(value = "passengerId") Long passengerId,
			@RequestParam(value = "flightId") Long flightId, 
			@Valid @RequestBody Reservation reservationDetails) {

		Reservation reservation = reservationRepository.findById(reservationId)
				.orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", reservationId));
		
		return flightRepository.findById(flightId).map(flight -> {
			Optional<Passenger> passenger = passengerRepository.findById(passengerId);
			reservation.setFlight(flight);
			reservation.setPassenger(passenger.get());
			reservation.setPassenger(reservationDetails.getPassenger());
			reservation.setCheckedIn(reservationDetails.getCheckedIn());
			reservation.setNumberOfBags(reservationDetails.getNumberOfBags());
			return reservationRepository.save(reservation);
		}).orElseThrow(() -> new ResourceNotFoundException("FlightId", "not found", flightId));
		
	}

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation deleteReservation
	 */
	@DeleteMapping("/reservation/{id}")
	public ResponseEntity<?> deleteReservation(@PathVariable(value = "id") Long reservationId) {
		Reservation reservation = reservationRepository.findById(reservationId)
				.orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", reservationId));

		reservationRepository.delete(reservation);

		return ResponseEntity.ok().build();
	}

}
