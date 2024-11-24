package ride_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String pickupLocation;
    private String dropLocation;
    private String status; // PENDING, ASSIGNED, STARTED, COMPLETED, CANCELLED

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters and Setters
}
