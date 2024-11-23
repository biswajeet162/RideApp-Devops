package com.driver_service;

import com.driver_service.model.Driver;
import com.driver_service.repository.DriverRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class DriverServiceApplication {

	private final DriverRepository driverRepository;

	public static void main(String[] args) {
		SpringApplication.run(DriverServiceApplication.class, args);
	}

}
