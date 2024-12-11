package com.driver_service.service;


import com.driver_service.model.Driver;
import com.driver_service.model.RideRequest;
import com.driver_service.model.availableDriversDTO.AvailableDriverResponseDTO;
import com.driver_service.model.availableDriversDTO.DriverInfoDTO;
import com.driver_service.model.availableDriversDTO.VehicleTypeDTO;
import com.driver_service.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DriversNearUserService {

    @Autowired
    private DriverRepository driverRepository;


    public AvailableDriverResponseDTO getAvailableDriversNearUser(RideRequest rideRequest, String userLocationFromMap) {
        // Parse the user's location into latitude and longitude
        String[] userCoords = userLocationFromMap.split(",");
        double userLat = Double.parseDouble(userCoords[0]);
        double userLng = Double.parseDouble(userCoords[1]);

        // Fetch all available drivers from the repository
        List<Driver> availableDrivers = driverRepository.findAllByStatus("AVAILABLE");

        // Calculate distances and sort by nearest drivers
        List<Driver> availableDriversList = availableDrivers.stream()
                .sorted(Comparator.comparingDouble(driver ->
                        calculateDistance(userLat, userLng, parseLat(driver.getLocation()), parseLng(driver.getLocation()))))
                .limit(5) // Return the top 5 nearest drivers
                .collect(Collectors.toList());


        return getAvailableDriverResponseDTO(rideRequest, userLocationFromMap, availableDrivers);

    }

    private AvailableDriverResponseDTO getAvailableDriverResponseDTO(RideRequest rideRequest, String userLocationFromMap, List<Driver> availableDrivers) {
        // Map drivers to DriverDTO and group by vehicle type
        Map<String, List<DriverInfoDTO>> groupedDrivers = availableDrivers.stream()
                .map(driver -> new DriverInfoDTO(
                        driver.getId(),
                        calculatePrice(driver.getVehicleType()),
                        driver.getLocation(),
                        calculateDistanceFromUser(userLocationFromMap, driver.getLocation()),
                        driver.getVehicleType(),
                        driver.getRating()
                ))
                .collect(Collectors.groupingBy(DriverInfoDTO::getVehicleType));

        // Convert grouped drivers to VehicleTypeDTO
        List<VehicleTypeDTO> vehicleTypeDTOList = groupedDrivers.entrySet().stream()
                .map(entry -> new VehicleTypeDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        // Create the final response DTO
        return new AvailableDriverResponseDTO(
                rideRequest.getUserId(),
                rideRequest.getPickupLocation(),
                rideRequest.getDropLocation(),
                userLocationFromMap,
                LocalDateTime.now(),
                vehicleTypeDTOList
        );
    }


    private Double calculatePrice(String vehicleType) {
        // Simplified pricing logic
        switch (vehicleType) {
            case "Bike":
                return 50.0; // Base price for bikes
            case "Mini":
                return 100.0;
            case "Sedan":
                return 200.0;
            case "Luxury":
                return 500.0;
            default:
                return 150.0;
        }
    }

    private Double calculateDistanceFromUser(String userLocation, String driverLocation) {
        // Parse user and driver coordinates
        String[] userCoords = userLocation.split(",");
        double userLat = Double.parseDouble(userCoords[0]);
        double userLng = Double.parseDouble(userCoords[1]);

        String[] driverCoords = driverLocation.split(",");
        double driverLat = Double.parseDouble(driverCoords[0]);
        double driverLng = Double.parseDouble(driverCoords[1]);

        // Calculate distance using the Haversine formula
        return calculateDistance(userLat, userLng, driverLat, driverLng);
    }


    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int EARTH_RADIUS = 6371; // Approx Earth radius in KM

        // Convert degrees to radians
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c; // Return distance in kilometers
    }

    private double parseLat(String location) {
        return Double.parseDouble(location.split(",")[0]);
    }

    private double parseLng(String location) {
        return Double.parseDouble(location.split(",")[1]);
    }
}
