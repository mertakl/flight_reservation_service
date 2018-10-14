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
import com.mertakl.springboot.flight.model.City;
import com.mertakl.springboot.flight.repository.CityRepository;
import com.mertakl.springboot.flight.repository.CountryRepository;

@RestController
@RequestMapping("/api")
public class CityController {

	@Autowired
	CityRepository cityRepository;

	@Autowired
	CountryRepository countryRepository;

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation 	//Get all cities
	 */
	@GetMapping("/cities")
	public List<City> getAllcitys() {
		return cityRepository.findAll();
	}
	

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation Get all cities by country id.
	 */
	@GetMapping("/countries/{countryId}/cities")
	public Page<City> getAllCitiesByCountryId(@PathVariable(value = "countryId") Long countryId, Pageable pageable) {
		return cityRepository.findByCountryId(countryId, pageable);
	}

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation Create city
	 */
	@PostMapping("/country/{countryId}/city")
	public City createCity(@PathVariable(value = "countryId") Long countryId, @Valid @RequestBody City city) {
		return countryRepository.findById(countryId).map(country -> {
			city.setCountry(country);
			return cityRepository.save(city);
		}).orElseThrow(() -> new ResourceNotFoundException("CountryId", "not found", countryId));
	}

	
	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation Get city by Id
	 */
	@GetMapping("/city/{id}")
	public City getcityById(@PathVariable(value = "id") Long cityId) {
		return cityRepository.findById(cityId).orElseThrow(() -> new ResourceNotFoundException("City", "id", cityId));
	}

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation Update city
	 */
	@PutMapping("/country/{countryId}/city/{cityId}")
	public City updatecity(@PathVariable(value = "countryId") Long countryId,
			@PathVariable(value = "cityId") Long cityId, @Valid @RequestBody City cityDetails) {

		City city = cityRepository.findById(cityId)
				.orElseThrow(() -> new ResourceNotFoundException("City", "id", cityId));

		return countryRepository.findById(countryId).map(country -> {
			city.setCityCode(cityDetails.getCityCode());
			city.setCityName(cityDetails.getCityName());
			city.setCountry(country);
			return cityRepository.save(city);
		}).orElseThrow(() -> new ResourceNotFoundException("CountryId", "not found", countryId));
	}


	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation deletecity
	 */
	@DeleteMapping("/city/{id}")
	public ResponseEntity<?> deletecity(@PathVariable(value = "id") Long cityId) {
		City city = cityRepository.findById(cityId)
				.orElseThrow(() -> new ResourceNotFoundException("City", "id", cityId));

		cityRepository.delete(city);

		return ResponseEntity.ok().build();
	}

}
