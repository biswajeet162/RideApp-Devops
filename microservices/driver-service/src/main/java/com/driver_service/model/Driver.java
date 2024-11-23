package com.driver_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "app_driver")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    private String vehicleType;
    private String status = "AVAILABLE";  // Initial status is "AVAILABLE"
    private String location;  // Store coordinates as a string or separate latitude/longitude fields
    private Double rating;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
