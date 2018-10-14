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
import com.mertakl.springboot.flight.model.AirlineCompany;
import com.mertakl.springboot.flight.repository.AirlineCompanyRepository;

@RestController
@RequestMapping("/api")
public class AirlineCompanyController {
	
	@Autowired
	AirlineCompanyRepository airlineCompanyRepository;
	
	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation getAllairlineCompanys
	 */
	@GetMapping("/airlineCompanys")
	public List<AirlineCompany> getAllairlineCompanys() {
	    return airlineCompanyRepository.findAll();
	}
	
	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation createairlineCompany
	 */
	@PostMapping("/airlineCompany")
	public AirlineCompany createairlineCompany(@Valid @RequestBody AirlineCompany airlineCompany) {
	    return airlineCompanyRepository.save(airlineCompany);
	}
	
	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation getairlineCompanyById
	 */
	@GetMapping("/airlineCompany/{id}")
	public AirlineCompany getairlineCompanyById(@PathVariable(value = "id") Long airlineCompanyId) {
	    return airlineCompanyRepository.findById(airlineCompanyId)
	            .orElseThrow(() -> new ResourceNotFoundException("AirlineCompany", "id", airlineCompanyId));
	}
	
	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation updateairlineCompany
	 */
	@PutMapping("/airlineCompany/{id}")
	public AirlineCompany updateairlineCompany(@PathVariable(value = "id") Long airlineCompanyId,
	                                        @Valid @RequestBody AirlineCompany airlineCompanyDetails) {

	    AirlineCompany airlineCompany = airlineCompanyRepository.findById(airlineCompanyId)
	            .orElseThrow(() -> new ResourceNotFoundException("AirlineCompany", "id", airlineCompanyId));
	    
	    airlineCompany.setCompanyName(airlineCompanyDetails.getCompanyName());
	  
	    AirlineCompany updatedairlineCompany = airlineCompanyRepository.save(airlineCompany);
	    return updatedairlineCompany;
	}
	
	/**
	 * Created by mertakl 14 Eki 2018 flight-reservation deleteairlineCompany
	 */
	@DeleteMapping("/airlineCompany/{id}")
	public ResponseEntity<?> deleteairlineCompany(@PathVariable(value = "id") Long airlineCompanyId) {
		AirlineCompany airlineCompany = airlineCompanyRepository.findById(airlineCompanyId)
	            .orElseThrow(() -> new ResourceNotFoundException("AirlineCompany", "id", airlineCompanyId));

		airlineCompanyRepository.delete(airlineCompany);

	    return ResponseEntity.ok().build();
	}

}
