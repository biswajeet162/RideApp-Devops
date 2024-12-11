package com.driver_service.model;


import lombok.Data;

@Data
public class RideRequest {

    private Long userId;
    private String pickupLocation;
    private String dropLocation;
    private String userCoordinates;

    // Getters and Setters
}
