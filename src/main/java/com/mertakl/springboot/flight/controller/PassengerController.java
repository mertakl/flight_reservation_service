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
import com.mertakl.springboot.flight.model.Flight;
import com.mertakl.springboot.flight.model.Passenger;
import com.mertakl.springboot.flight.repository.CityRepository;
import com.mertakl.springboot.flight.repository.CountryRepository;
import com.mertakl.springboot.flight.repository.FlightRepository;
import com.mertakl.springboot.flight.repository.PassengerRepository;

/**
 * @author makel
 *
 */
@RestController
@RequestMapping("/api")
public class PassengerController {

	@Autowired
	PassengerRepository passengerRepository;

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	FlightRepository flightRepository;

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation Get passengers
	 */
	@GetMapping("/passengers")
	public List<Passenger> getAllPassengers() {
		return passengerRepository.findAll();
	}

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation Get passengers by country
	 */
	@GetMapping("/countries/{countryId}/passengers")
	public Page<City> getAllPassengersByCountryId(@PathVariable(value = "countryId") Long countryId,
			Pageable pageable) {
		return passengerRepository.findByCountryId(countryId, pageable);
	}

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation create passenger
	 */
	@PostMapping("/passenger")
	public Passenger createPassenger(@RequestParam(value = "countryId") Long countryId,
			@Valid @RequestBody Passenger passenger) {

		return countryRepository.findById(countryId).map(country -> {
			passenger.setCountry(country);
			return passengerRepository.save(passenger);
		}).orElseThrow(() -> new ResourceNotFoundException("CountryId", "not found", countryId));

	}

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation Get passenger by id.
	 */
	@GetMapping("/passenger/{id}")
	public Passenger getPassengerById(@PathVariable(value = "id") Long passengerId) {
		return passengerRepository.findById(passengerId)
				.orElseThrow(() -> new ResourceNotFoundException("Passenger", "id", passengerId));
	}

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation Update passenger
	 */
	@PutMapping("/passenger")
	public Passenger updatePassenger(@RequestParam(value = "countryId") Long countryId,
			@RequestParam(value = "passengerId") Long passengerId, @Valid @RequestBody Passenger passengerDetails) {

		Passenger passenger = passengerRepository.findById(passengerId)
				.orElseThrow(() -> new ResourceNotFoundException("Passenger", "id", passengerId));

		return countryRepository.findById(countryId).map(country -> {
			passenger.setCountry(country);
			passenger.setFirstName(passengerDetails.getFirstName());
			passenger.setLastName(passenger.getLastName());
			passenger.setIdentityNumber(passengerDetails.getIdentityNumber());
			passenger.setPassportNumber(passengerDetails.getPassportNumber());
			return passengerRepository.save(passenger);
		}).orElseThrow(() -> new ResourceNotFoundException("CountryId", "not found", countryId));

	}

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation delete passenger
	 */
	@DeleteMapping("/passenger/{id}")
	public ResponseEntity<?> deletePassenger(@PathVariable(value = "id") Long passengerId) {
		Passenger passenger = passengerRepository.findById(passengerId)
				.orElseThrow(() -> new ResourceNotFoundException("Passenger", "id", passengerId));

		passengerRepository.delete(passenger);

		return ResponseEntity.ok().build();
	}

}
