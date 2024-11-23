package com.driver_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverDTO {
    private Long id;
    private Long userId;
    private String vehicleType;
    private String status;
    private String location;
    private Double rating;
}
