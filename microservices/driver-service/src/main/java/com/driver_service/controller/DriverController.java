package com.driver_service.controller;

import com.driver_service.model.Driver;
import com.driver_service.model.DriverDTO;
import com.driver_service.repository.UserRepository;
import com.driver_service.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;


    // Existing onboarding driver endpoint
    @GetMapping("/onboard/{id}")
    public ResponseEntity<DriverDTO> onboardDriver(@PathVariable ("id") Long userId) {
        Driver savedDriver = driverService.onboardDriver(userId);
        DriverDTO driverDTO = new DriverDTO(savedDriver.getId(), savedDriver.getUser().getId(), savedDriver.getVehicleType(), savedDriver.getStatus(), savedDriver.getLocation(), savedDriver.getRating());
        return ResponseEntity.ok(driverDTO);
    }

    // Existing update driver location endpoint
    @PutMapping("/{driverId}/update-location")
    public ResponseEntity<DriverDTO> updateDriverLocation(@PathVariable Long driverId) {
        Driver updatedDriver = driverService.updateDriverLocation(driverId);
        if (updatedDriver != null) {
            DriverDTO driverDTO = new DriverDTO(updatedDriver.getId(), updatedDriver.getUser().getId(), updatedDriver.getVehicleType(), updatedDriver.getStatus(), updatedDriver.getLocation(), updatedDriver.getRating());
            return ResponseEntity.ok(driverDTO);
        }
        return ResponseEntity.notFound().build();
    }

    // New endpoint to get available drivers for the Ride Service
    @GetMapping("/available")
    public ResponseEntity<List<DriverDTO>> getAvailableDrivers() {
        List<Driver> availableDrivers = driverService.getAvailableDrivers();
        List<DriverDTO> driverDTOs = availableDrivers.stream()
                .map(driver -> new DriverDTO(driver.getId(), driver.getUser().getId(), driver.getVehicleType(), driver.getStatus(), driver.getLocation(), driver.getRating()))
                .toList();
        return ResponseEntity.ok(driverDTOs);
    }

    // New endpoint to get a specific driver by ID
    @GetMapping("/{driverId}")
    public ResponseEntity<DriverDTO> getDriverDetails(@PathVariable Long driverId) {
        Driver driver = driverService.getDriverById(driverId);
        if (driver != null) {
            DriverDTO driverDTO = new DriverDTO(driver.getId(), driver.getUser().getId(), driver.getVehicleType(), driver.getStatus(), driver.getLocation(), driver.getRating());
            return ResponseEntity.ok(driverDTO);
        }
        return ResponseEntity.notFound().build();
    }

    // New endpoint to update driver status (e.g., "ON_RIDE", "UNAVAILABLE")
    @PutMapping("/{driverId}/status")
    public ResponseEntity<DriverDTO> updateDriverStatus(@PathVariable Long driverId, @RequestParam String status) {
        Driver updatedDriver = driverService.updateDriverStatus(driverId, status);
        if (updatedDriver != null) {
            DriverDTO driverDTO = new DriverDTO(updatedDriver.getId(), updatedDriver.getUser().getId(), updatedDriver.getVehicleType(), updatedDriver.getStatus(), updatedDriver.getLocation(), updatedDriver.getRating());
            return ResponseEntity.ok(driverDTO);
        }
        return ResponseEntity.notFound().build();
    }
}
