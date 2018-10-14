package com.mertakl.springboot.flight.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mertakl.springboot.flight.model.City;

public interface CityRepository extends JpaRepository<City, Long>{

	Page<City> findByCountryId(Long countryId, Pageable pageable);

}
