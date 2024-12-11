package com.driver_service.model.websocket;

import lombok.Data;

@Data
public class RideAcceptanceSocketModel {
    private Integer driverId;
    private Integer userId;
    private Integer rideId;
    private String response; // "accepted" or "rejected"
}
