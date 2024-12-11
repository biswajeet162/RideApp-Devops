package ride_service.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
    private String userId;

    private String vehicleType;
    private String status; // AVAILABLE, ON_RIDE, UNAVAILABLE
    private String location;
    private Double rating;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Driver(long l, String suv, String available, String s, double v) {
        this.id = l;
        this.vehicleType = suv;
        this.status = available;
        this.location = s;
        this.rating = v;
    }
}
