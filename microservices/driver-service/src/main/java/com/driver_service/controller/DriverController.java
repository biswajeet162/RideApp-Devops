package com.driver_service.controller;

import com.driver_service.exceptions.UserAlreadyExist;
import com.driver_service.model.Driver;
import com.driver_service.model.DriverDTO;
import com.driver_service.model.RideRequest;
import com.driver_service.model.User;
import com.driver_service.model.availableDriversDTO.AvailableDriverResponseDTO;
import com.driver_service.service.DriverService;
import com.driver_service.service.DriversNearUserService;
import com.driver_service.service.MapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
@Slf4j
public class DriverController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private DriversNearUserService driversNearUserService;

    @Autowired
    private MapService mapService;


    // Existing onboarding driver endpoint
    @PostMapping("/onboard")
    public DriverDTO onboardDriver(@RequestBody User registeredDriverUser) throws UserAlreadyExist {
        Driver savedDriver = driverService.onboardDriver(registeredDriverUser);
        DriverDTO driverDTO = new DriverDTO(savedDriver.getId(), savedDriver.getUser().getId(), savedDriver.getUser().getName(), savedDriver.getUser().getEmail(),savedDriver.getUser().getPhoneNumber(), savedDriver.getVehicleType(), savedDriver.getStatus(), savedDriver.getLocation(), savedDriver.getRating());
        return driverDTO;
    }

    // Get available drivers for the ride
    @PostMapping(value = "/nearby-available-drivers", produces = "application/json")
    public ResponseEntity<AvailableDriverResponseDTO> getAvailableDriversNearToYou(@RequestBody RideRequest rideRequest) {
        try {
            String userLocationFromMap = mapService.getRandomLocationFromSrcDest(rideRequest.getPickupLocation());
            AvailableDriverResponseDTO availableDriversNearUser = driversNearUserService.getAvailableDriversNearUser(rideRequest, userLocationFromMap);
            log.info("availableDrivers: {}", availableDriversNearUser);
            return ResponseEntity.ok(availableDriversNearUser);
        } catch (Exception e) {
            log.info("availableDrivers error: {}", e);
        }
        return null;
    }

    // Existing update driver location endpoint
    @PutMapping("/{driverId}/update-location")
    public ResponseEntity<DriverDTO> updateDriverLocation(@PathVariable Long driverId) {
        Driver updatedDriver = driverService.updateDriverLocation(driverId);
        if (updatedDriver != null) {
            DriverDTO driverDTO = new DriverDTO(updatedDriver.getId(), updatedDriver.getUser().getId(), updatedDriver.getUser().getName(), updatedDriver.getUser().getEmail(),updatedDriver.getUser().getPhoneNumber(), updatedDriver.getVehicleType(), updatedDriver.getStatus(), updatedDriver.getLocation(), updatedDriver.getRating());
            return ResponseEntity.ok(driverDTO);
        }
        return ResponseEntity.notFound().build();
    }

    // New endpoint to get available drivers for the Ride Service
    @GetMapping("/available")
    public ResponseEntity<List<DriverDTO>> getAvailableDrivers() {
        List<Driver> availableDrivers = driverService.getAvailableDrivers();
        List<DriverDTO> driverDTOs = availableDrivers.stream()
                .map(driver -> new DriverDTO(driver.getId(), driver.getUser().getId(), driver.getUser().getName(), driver.getUser().getEmail(),driver.getUser().getPhoneNumber(), driver.getVehicleType(), driver.getStatus(), driver.getLocation(), driver.getRating()))
                .toList();
        return ResponseEntity.ok(driverDTOs);
    }

    // New endpoint to get a specific driver by ID
    @GetMapping("/{driverId}")
    public ResponseEntity<DriverDTO> getDriverDetails(@PathVariable Long driverId) {
        Driver driver = driverService.getDriverById(driverId);
        if (driver != null) {
            DriverDTO driverDTO = new DriverDTO(driver.getId(), driver.getUser().getId(), driver.getUser().getName(), driver.getUser().getEmail(),driver.getUser().getPhoneNumber(), driver.getVehicleType(), driver.getStatus(), driver.getLocation(), driver.getRating());
            return ResponseEntity.ok(driverDTO);
        }
        return ResponseEntity.notFound().build();
    }

    // New endpoint to update driver status (e.g., "ON_RIDE", "UNAVAILABLE")
    @PutMapping("/{driverId}/status")
    public ResponseEntity<DriverDTO> updateDriverStatus(@PathVariable Long driverId, @RequestParam String status) {
        Driver updatedDriver = driverService.updateDriverStatus(driverId, status);
        if (updatedDriver != null) {
            DriverDTO driverDTO = new DriverDTO(updatedDriver.getId(), updatedDriver.getUser().getId(), updatedDriver.getUser().getName(), updatedDriver.getUser().getEmail(), updatedDriver.getUser().getPhoneNumber(), updatedDriver.getVehicleType(), updatedDriver.getStatus(), updatedDriver.getLocation(), updatedDriver.getRating());
            return ResponseEntity.ok(driverDTO);
        }
        return ResponseEntity.notFound().build();
    }
}
