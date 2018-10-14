package com.mertakl.springboot.flight.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mertakl.springboot.flight.model.Airports;

public interface AirportsRepository extends JpaRepository<Airports, Long>{

	Page<Airports> findByCityId(Long cityId, Pageable pageable);

}
