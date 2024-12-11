package com.driver_service.service;


import com.driver_service.exceptions.UserAlreadyExist;
import com.driver_service.model.Driver;
import com.driver_service.model.User;
import com.driver_service.repository.DriverRepository;
import com.driver_service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;


    @Autowired private
    UserRepository userRepository;

    @Autowired
    private MapService mapService;

    // Onboard a new driver
    @Transactional  // Ensure the method runs in a single transaction
    public Driver onboardDriver(User registeredDriverUser) throws UserAlreadyExist {
        // Check if the user already exists
        Driver existingDriver = driverRepository.findByUserId(registeredDriverUser.getId());
        if (existingDriver != null) {
            throw new UserAlreadyExist("This is Existing User..............updated the Coordinates");
        }

        log.info("This is New User");
        User savedNewUser = userRepository.save(registeredDriverUser);

        // Create and save the new driver
        Driver driver = new Driver();
        driver.setUser(savedNewUser);

        //driver.setVehicleType();
        driver.setLocation(mapService.getRandomLocation());
        driver.setCreatedAt(LocalDateTime.now());
        driver.setUpdatedAt(LocalDateTime.now());
        driver.setRating(0.0);

        return driverRepository.save(driver);
    }

    // Update the driver's location periodically
    public Driver updateDriverLocation(Long driverId) {
        Driver driver = driverRepository.findById(driverId).orElse(null);
        if (driver != null) {
            driver.setLocation(mapService.getRandomLocation());  // Mock location update
            driver.setUpdatedAt(java.time.LocalDateTime.now());
            return driverRepository.save(driver);
        }
        return null;
    }

    // Get available drivers (status "AVAILABLE")
    public List<Driver> getAvailableDrivers() {
        return driverRepository.findAllByStatus("AVAILABLE");
    }

    // Get driver by ID
    public Driver getDriverById(Long driverId) {
        return driverRepository.findById(driverId).orElse(null);
    }

    // Update the driver's status (e.g., "ON_RIDE" or "UNAVAILABLE")
    public Driver updateDriverStatus(Long driverId, String status) {
        Driver driver = driverRepository.findById(driverId).orElse(null);
        if (driver != null) {
            driver.setStatus(status);
            driver.setUpdatedAt(java.time.LocalDateTime.now());
            return driverRepository.save(driver);
        }
        return null;
    }


}
