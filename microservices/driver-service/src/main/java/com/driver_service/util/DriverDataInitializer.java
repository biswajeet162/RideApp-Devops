package com.driver_service.util;



import com.driver_service.model.Driver;
import com.driver_service.model.User;
import com.driver_service.repository.DriverRepository;
import com.driver_service.service.MapService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class DriverDataInitializer {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private MapService mapService;


//    private final UserRepository userRepository;



//    @PostConstruct
    public void init() {
        // List of mock users
        List<User> users = Arrays.asList(
                new User(3L, "John Doe", "john.doe@example.com", "password123", "1234567890", "DRIVER"),
                new User(4L, "Jane Smith", "jane.smith@example.com", "password123", "1234567891", "DRIVER"),
                new User(4L, "Robert Brown", "robert.brown@example.com", "password123", "1234567892", "DRIVER"),
                new User(4L, "Emily Davis", "emily.davis@example.com", "password123", "1234567893", "DRIVER"),
                new User(4L, "Michael Johnson", "michael.johnson@example.com", "password123", "1234567894", "DRIVER"),
                new User(4L, "Sarah Wilson", "sarah.wilson@example.com", "password123", "1234567895", "DRIVER"),
                new User(4L, "David Lee", "david.lee@example.com", "password123", "1234567896", "DRIVER"),
                new User(4L, "Emma Walker", "emma.walker@example.com", "password123", "1234567897", "DRIVER"),
                new User(4L, "Daniel Harris", "daniel.harris@example.com", "password123", "1234567898", "DRIVER"),
                new User(4L, "Sophia Clark", "sophia.clark@example.com", "password123", "1234567899", "DRIVER")
        );


        // List of vehicle types
        List<String> vehicleTypes = Arrays.asList("Sedan", "SUV", "Hatchback", "Luxury", "Mini");

        // Create and save drivers
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
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
