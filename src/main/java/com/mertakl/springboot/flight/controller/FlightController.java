package com.mertakl.springboot.flight.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.mertakl.springboot.flight.model.Aircraft;
import com.mertakl.springboot.flight.model.City;
import com.mertakl.springboot.flight.model.Flight;
import com.mertakl.springboot.flight.repository.AircraftRepository;
import com.mertakl.springboot.flight.repository.CityRepository;
import com.mertakl.springboot.flight.repository.FlightRepository;

@RestController
@RequestMapping("/api")
public class FlightController {

	@Autowired
	FlightRepository flightRepository;

	@Autowired
	CityRepository cityRepository;

	@Autowired
	AircraftRepository aircraftRepository;

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation getAllFlights
	 */
	@GetMapping("/flights")
	public List<Flight> getAllFlights() {
		return flightRepository.findAll();
	}

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation createFlight
	 */
	@PostMapping("/flight")
	public Flight createFlight(@RequestParam(value = "aircraftId") Long aircraftId,
			@RequestParam(value = "departureCityId") Long departureCityId,
			@RequestParam(value = "arrivalCityId") Long arrivalCityId, @Valid @RequestBody Flight flight) {

		return cityRepository.findById(departureCityId).map(departureCity -> {
			Optional<City> arrivalCity = cityRepository.findById(arrivalCityId);
			Optional<Aircraft> operatingAircraft = aircraftRepository.findById(aircraftId);
			flight.setDepartureCity(departureCity);
			flight.setArrivalCity(arrivalCity.get());
			flight.setOperatingAircraft(operatingAircraft.get());
			return flightRepository.save(flight);
		}).orElseThrow(() -> new ResourceNotFoundException("DepartureCityId", "not found", departureCityId));
	}

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation getFlightrById
	 */
	@GetMapping("/flight/{id}")
	public Flight getFlightrById(@PathVariable(value = "id") Long flightId) {
		return flightRepository.findById(flightId)
				.orElseThrow(() -> new ResourceNotFoundException("Flight", "id", flightId));
	}

	// Update flight
	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation updatePassenger
	 */
	@PutMapping("/flight")
	public Flight updatePassenger(@RequestParam(value = "flightId") Long flightId,
			@RequestParam(value = "aircraftId") Long aircraftId,
			@RequestParam(value = "departureCityId") Long departureCityId,
			@RequestParam(value = "arrivalCityId") Long arrivalCityId, @Valid @RequestBody Flight flightDetails) {

		Flight flight = flightRepository.findById(flightId)
				.orElseThrow(() -> new ResourceNotFoundException("Flight", "id", flightId));
		return cityRepository.findById(departureCityId).map(departureCity -> {
			Optional<City> arrivalCity = cityRepository.findById(arrivalCityId);
			Optional<Aircraft> operatingAircraft = aircraftRepository.findById(aircraftId);

			flight.setDepartureCity(departureCity);
			flight.setArrivalCity(arrivalCity.get());
			flight.setOperatingAircraft(operatingAircraft.get());
			flight.setDateOfDeparture(flightDetails.getDateOfDeparture());
			flight.setEstimatedDuration(flightDetails.getEstimatedDuration());
			flight.setFlightNumber(flightDetails.getFlightNumber());

			return flightRepository.save(flight);
		}).orElseThrow(() -> new ResourceNotFoundException("DepartureCityId", "not found", departureCityId));

	}

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation deleteFlight
	 */
	@DeleteMapping("/flight/{id}")
	public ResponseEntity<?> deleteFlight(@PathVariable(value = "id") Long flightId) {
		Flight flight = flightRepository.findById(flightId)
				.orElseThrow(() -> new ResourceNotFoundException("Flight", "id", flightId));

		flightRepository.delete(flight);

		return ResponseEntity.ok().build();
	}

}
