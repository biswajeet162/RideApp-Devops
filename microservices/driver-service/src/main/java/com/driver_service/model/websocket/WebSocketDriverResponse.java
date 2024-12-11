package com.driver_service.model.websocket;

import lombok.Data;

@Data
public class WebSocketDriverResponse {
    private Integer driverId;
    private Integer rideId;
    private String response; // "accepted" or "rejected"

    // Getters and Setters
}
