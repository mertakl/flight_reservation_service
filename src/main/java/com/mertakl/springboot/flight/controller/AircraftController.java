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
import com.mertakl.springboot.flight.model.Aircraft;
import com.mertakl.springboot.flight.model.City;
import com.mertakl.springboot.flight.repository.AircraftRepository;
import com.mertakl.springboot.flight.repository.AirlineCompanyRepository;

@RestController
@RequestMapping("/api")
public class AircraftController {

	@Autowired
	AircraftRepository aircraftRepository;

	@Autowired
	AirlineCompanyRepository airlineCompanyRepository;

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation getAllAircrafts
	 */
	@GetMapping("/aircrafts")
	public List<Aircraft> getAllAircrafts() {
		return aircraftRepository.findAll();
	}

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation getAllAircraftsByAirlineCompany
	 */
	@GetMapping("/airlineCompanies/{companyId}/aircrafts")
	public Page<Aircraft> getAllAircraftsByAirlineCompany(@PathVariable(value = "companyId") Long companyId,
			Pageable pageable) {
		return aircraftRepository.findByCompanyId(companyId, pageable);
	}

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation createAircraft
	 */
	@PostMapping("/airlineCompany/{companyId}/aircraft")
	public Aircraft createAircraft(@PathVariable(value = "companyId") Long companyId,
			@Valid @RequestBody Aircraft aircraft) {
		return airlineCompanyRepository.findById(companyId).map(airlineCompany -> {
			aircraft.setCompanyName(airlineCompany);
			return aircraftRepository.save(aircraft);
		}).orElseThrow(() -> new ResourceNotFoundException("CountryId", "not found", companyId));
	}

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation getAircraftById
	 */
	@GetMapping("/aircraft/{id}")
	public Aircraft getAircraftById(@PathVariable(value = "id") Long aircraftId) {
		return aircraftRepository.findById(aircraftId)
				.orElseThrow(() -> new ResourceNotFoundException("Aircraft", "id", aircraftId));
	}

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation updateAircraft
	 */
	@PutMapping("/airlineCompany/{companyId}/aircraft/{aircraftId}")
	public Aircraft updateAircraft(@PathVariable(value = "companyId") Long companyId,
			@PathVariable(value = "aircraftId") Long aircraftId, @Valid @RequestBody Aircraft aircraftDetails) {

		Aircraft aircraft = aircraftRepository.findById(aircraftId)
				.orElseThrow(() -> new ResourceNotFoundException("Aircraft", "id", aircraftId));

		return airlineCompanyRepository.findById(companyId).map(company -> {
			aircraft.setAircraftModel(aircraftDetails.getAircraftModel());
			aircraft.setAircraftName(aircraftDetails.getAircraftName());
			aircraft.setCompanyName(company);
			aircraft.setNumberOfSeats(aircraftDetails.getNumberOfSeats());
			return aircraftRepository.save(aircraft);
		}).orElseThrow(() -> new ResourceNotFoundException("CompanyId", "not found", companyId));
	}

	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation deleteAircraft
	 */
	@DeleteMapping("/aircraft/{id}")
	public ResponseEntity<?> deleteAircraft(@PathVariable(value = "id") Long aircraftId) {
		Aircraft aircraft = aircraftRepository.findById(aircraftId)
				.orElseThrow(() -> new ResourceNotFoundException("Aircraft", "id", aircraftId));

		aircraftRepository.delete(aircraft);

		return ResponseEntity.ok().build();
	}
}
