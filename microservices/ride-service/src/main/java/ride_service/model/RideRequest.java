package ride_service.model;


import lombok.Data;

@Data
public class RideRequest {

    private Long userId;
    private String pickupLocation;
    private String dropLocation;

    // Getters and Setters
}
