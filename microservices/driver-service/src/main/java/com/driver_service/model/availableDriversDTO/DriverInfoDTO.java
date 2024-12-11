package com.driver_service.model.availableDriversDTO;

import lombok.Data;

@Data
public class DriverInfoDTO {
    private Long driverId;
    private Double price; // Price for the ride
    private String driverLocation;
    private Double distanceFromUser; // Calculated distance from the user
    private String vehicleType;
    private Double rating;

    // Getters and Setters
    public DriverInfoDTO(Long driverId, Double price,String driverLocation, Double distanceFromUser, String vehicleType, Double rating) {
        this.driverId = driverId;
        this.price = price;
        this.driverLocation = driverLocation;
        this.distanceFromUser = distanceFromUser;
        this.vehicleType = vehicleType;
        this.rating = rating;
    }


    @Override
    public String toString() {
        return "DriverInfoDTO{" +
                "driverId=" + driverId +
                ", price=" + price +
                ", distanceFromUser=" + distanceFromUser +
                ", vehicleType='" + vehicleType + '\'' +
                ", rating=" + rating +
                '}';
    }
}
