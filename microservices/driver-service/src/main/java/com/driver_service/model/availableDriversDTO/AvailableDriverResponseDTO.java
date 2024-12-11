package com.driver_service.model.availableDriversDTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AvailableDriverResponseDTO {
    private Long userId;
    private String userPickupLocation;
    private String userDropLocation;
    private String userCoordinates;
    private LocalDateTime driverLocationUpdatesAt;
    private List<VehicleTypeDTO> drivers;

    // Getters and Setters
    public AvailableDriverResponseDTO(Long userId, String pickupLocation, String dropLocation, String userCoordinates,
                           LocalDateTime driverLocationUpdatesAt, List<VehicleTypeDTO> drivers) {
        this.userId = userId;
        this.userPickupLocation = pickupLocation;
        this.userDropLocation = dropLocation;
        this.userCoordinates = userCoordinates;
        this.driverLocationUpdatesAt = driverLocationUpdatesAt;
        this.drivers = drivers;
    }

    @Override
    public String toString() {
        return "AvailableDriverResponseDTO{" +
                "userId=" + userId +
                ", userPickupLocation='" + userPickupLocation + '\'' +
                ", userDropLocation='" + userDropLocation + '\'' +
                ", userCoordinates='" + userCoordinates + '\'' +
                ", driverLocationUpdatesAt=" + driverLocationUpdatesAt +
                ", drivers=" + drivers +
                '}';
    }
}
