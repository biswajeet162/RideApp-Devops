package com.driver_service.service;


import com.driver_service.model.Driver;
import com.driver_service.model.User;
import com.driver_service.repository.DriverRepository;
import com.driver_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;


    @Autowired private
    UserRepository userRepository;

    @Autowired
    private MapService mapService;

    // Onboard a new driver
    public Driver onboardDriver(Long userId) {


////        fetch User with driverId and set in driver.setUser();
        Optional<User> user = userRepository.findById(userId);


        Driver driver = new Driver();
        if(user.isEmpty()) {
            driver.setUser(new User());
        }
        else{
            driver.setUser(user.get());
        }

        driver.setLocation(mapService.getRandomLocation());  // Set mock location for MVP
        driver.setCreatedAt(java.time.LocalDateTime.now());
        driver.setUpdatedAt(java.time.LocalDateTime.now());
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
