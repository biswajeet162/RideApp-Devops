package com.driver_service;

import com.driver_service.model.Driver;
import com.driver_service.model.User;
import com.driver_service.repository.DriverRepository;
import com.driver_service.repository.UserRepository;
import com.driver_service.service.MapService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class DriverServiceApplication {

	private final DriverRepository driverRepository;

	private final UserRepository userRepository;

	private final MapService mapService;

	public static void main(String[] args) {
		SpringApplication.run(DriverServiceApplication.class, args);
	}


//	@PostConstruct
//	public void init() {
//		// List of mock users
//		List<User> users = Arrays.asList(
//				new User(3L, "John Doe", "john.doe@example.com", "1234567890", "DRIVER"),
//				new User(4L, "Jane Smith", "jane.smith@example.com", "1234567891", "DRIVER"),
//				new User(4L, "Robert Brown", "robert.brown@example.com", "1234567892", "DRIVER"),
//				new User(4L, "Emily Davis", "emily.davis@example.com", "1234567893", "DRIVER"),
//				new User(4L, "Michael Johnson", "michael.johnson@example.com", "1234567894", "DRIVER"),
//				new User(4L, "Sarah Wilson", "sarah.wilson@example.com", "1234567895", "DRIVER"),
//				new User(4L, "David Lee", "david.lee@example.com", "1234567896", "DRIVER"),
//				new User(4L, "Emma Walker", "emma.walker@example.com", "1234567897", "DRIVER"),
//				new User(4L, "Daniel Harris", "daniel.harris@example.com", "1234567898", "DRIVER"),
//				new User(4L, "Sophia Clark", "sophia.clark@example.com", "1234567899", "DRIVER")
//		);
//
//
//
//		userRepository.saveAll(users);
//	}

	@PostConstruct
	public void addSampleUsers() {
		for (int i = 1; i <= 10; i++) {
			User user = new User();
			user.setName("Sample User " + i);
			user.setEmail("user" + i + "@example.com");
			user.setPassword("pass" + i);
			user.setRole(i % 2 == 0 ? "driver" : "user"); // Alternate between "user" and "driver"
			user.setPhoneNumber("123456789" + i); // Unique phone number for each user

			userRepository.save(user);
		}

		List<User> allUsers = userRepository.findAll();
		int users_size = allUsers.size();
		// List of vehicle types
		List<String> vehicleTypes = Arrays.asList("Sedan", "SUV", "Hatchback", "Luxury", "Mini");

		for (int i = 0; i < users_size; i++) {
            User user = allUsers.get(i);
            Driver driver = new Driver();
            driver.setUser(user);
            driver.setVehicleType(vehicleTypes.get(i % vehicleTypes.size())); // Cycle through vehicle types
            driver.setStatus("AVAILABLE");
            driver.setLocation(mapService.getRandomLocation()); // Use mock location for MVP
            driver.setRating(4.0 + (i % 3) * 0.5); // Assign ratings between 4.0 and 5.0
            driver.setCreatedAt(LocalDateTime.now());
            driver.setUpdatedAt(LocalDateTime.now());

            driverRepository.save(driver);
        }


	}

}