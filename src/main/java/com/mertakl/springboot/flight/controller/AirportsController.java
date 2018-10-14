package com.mertakl.springboot.flight.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.mertakl.springboot.flight.exception.ResourceNotFoundException;
import com.mertakl.springboot.flight.model.Airports;
import com.mertakl.springboot.flight.model.City;
import com.mertakl.springboot.flight.repository.AirportsRepository;
import com.mertakl.springboot.flight.repository.CityRepository;

@RestController
@RequestMapping("/api")
public class AirportsController {

	@Autowired
	AirportsRepository airportsRepository;
	
	@Autowired
	CityRepository cityRepository;

	/** 
	 * Created by mertakl 14 Eki 2018 flight-reservation getAllAirports
	 */
	@GetMapping("/airportss")
	public List<Airports> getAllAirports() {
		return airportsRepository.findAll();
	}

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation getAllAirportsByCityId
	 */
	@GetMapping("/cities/{cityId}/airports")
	public Page<Airports> getAllAirportsByCityId(@PathVariable(value = "cityId") Long cityId, Pageable pageable) {
		return airportsRepository.findByCityId(cityId, pageable);
	}
	

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation createAirports
	 */
	@PostMapping("/city/{cityId}/airport")
	public Airports createAirports(@PathVariable(value = "cityId") Long cityId, @Valid @RequestBody Airports airports) {
		return cityRepository.findById(cityId).map(city -> {
			airports.setCity(city);
			return airportsRepository.save(airports);
		}).orElseThrow(() -> new ResourceNotFoundException("CityId", "not found", cityId));
	}

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation getAirportsById
	 */
	@GetMapping("/airports/{id}")
	public Airports getAirportsById(@PathVariable(value = "id") Long airportsId) {
		return airportsRepository.findById(airportsId)
				.orElseThrow(() -> new ResourceNotFoundException("Airports", "id", airportsId));
	}

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation updateAirport
	 */
	@PutMapping("/airport/{airportId}/city/{cityId}")
	public Airports updateAirport(@PathVariable(value = "airportId") Long airportId,
			@PathVariable(value="cityId") Long cityId,
			@Valid @RequestBody Airports airportsDetails) {

		Airports airport = airportsRepository.findById(airportId)
				.orElseThrow(() -> new ResourceNotFoundException("Airport", "id", airportId));

		return cityRepository.findById(cityId).map(city -> {
			airport.setCity(city);
			airport.setAirportCode(airportsDetails.getAirportCode());
			airport.setAirportName(airportsDetails.getAirportName());
			return airportsRepository.save(airport);
		}).orElseThrow(() -> new ResourceNotFoundException("CityId", "not found", cityId));
		
	}

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation deleteairports
	 */
	@DeleteMapping("/airports/{id}")
	public ResponseEntity<?> deleteairports(@PathVariable(value = "id") Long airportsId) {
		Airports airports = airportsRepository.findById(airportsId)
				.orElseThrow(() -> new ResourceNotFoundException("Airports", "id", airportsId));

		airportsRepository.delete(airports);

		return ResponseEntity.ok().build();
	}

}
