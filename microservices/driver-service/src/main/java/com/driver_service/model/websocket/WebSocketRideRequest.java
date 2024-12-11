package com.driver_service.model.websocket;

import lombok.Data;

import java.util.List;

@Data
public class WebSocketRideRequest {
    private String userName;
    private String pickupLocation;
    private List<Integer> driverIds;

    // Getters and Setters
}
