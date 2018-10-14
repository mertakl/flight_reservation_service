package com.mertakl.springboot.flight.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.mertakl.springboot.flight.exception.ResourceNotFoundException;
import com.mertakl.springboot.flight.model.Country;
import com.mertakl.springboot.flight.repository.CountryRepository;

@RestController
@RequestMapping("/api")
public class CountryController {

	@Autowired
	CountryRepository countryRepository;

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation getAllcountrys
	 */
	@GetMapping("/countrys")
	public List<Country> getAllcountrys() {
		return countryRepository.findAll();
	}

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation createcountry
	 */
	@PostMapping("/country")
	public Country createcountry(@Valid @RequestBody Country country) {
		return countryRepository.save(country);
	}

	/** 
	 * Created by mertakl 14 Eki 2018 flight-reservation getcountryById
	 */
	@GetMapping("/country/{id}")
	public Country getcountryById(@PathVariable(value = "id") Long countryId) {
		return countryRepository.findById(countryId)
				.orElseThrow(() -> new ResourceNotFoundException("Country", "id", countryId));
	}

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation updatecountry
	 */
	@PutMapping("/country/{id}")
	public Country updatecountry(@PathVariable(value = "id") Long countryId,
			@Valid @RequestBody Country countryDetails) {

		Country country = countryRepository.findById(countryId)
				.orElseThrow(() -> new ResourceNotFoundException("Country", "id", countryId));

		country.setCountryName(countryDetails.getCountryName());

		Country updatedcountry = countryRepository.save(country);
		return updatedcountry;
	}

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation deletecountry
	 */
	@DeleteMapping("/country/{id}")
	public ResponseEntity<?> deletecountry(@PathVariable(value = "id") Long countryId) {
		Country country = countryRepository.findById(countryId)
				.orElseThrow(() -> new ResourceNotFoundException("country", "id", countryId));

		countryRepository.delete(country);

		return ResponseEntity.ok().build();
	}

}
