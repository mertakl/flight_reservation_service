package com.mertakl.springboot.flight.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mertakl.springboot.flight.model.Aircraft;

public interface AircraftRepository extends JpaRepository<Aircraft, Long> {

	Page<Aircraft> findByCompanyId(Long companyId, Pageable pageable);

}
