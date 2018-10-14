package com.mertakl.springboot.flight;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = { 
		FlightReservationApplication.class,
		Jsr310JpaConverters.class 
})
public class FlightReservationApplication {
	
	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("Europe/Istanbul"));
	}

	public static void main(String[] args) {
		SpringApplication.run(FlightReservationApplication.class, args);
	}
}

