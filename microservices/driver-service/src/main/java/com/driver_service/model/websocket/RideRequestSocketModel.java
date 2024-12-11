package com.driver_service.model.websocket;

import lombok.Data;

import java.util.List;

@Data
public class RideRequestSocketModel {

    private List<Long> driverIds;  // List of selected driver IDs
    private String pickupLocation; // Pickup location
    private Long userId;           // User ID

    // Getters and Setters
    public List<Long> getDriverIds() {
        return driverIds;
    }

    public void setDriverIds(List<Long> driverIds) {
        this.driverIds = driverIds;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
