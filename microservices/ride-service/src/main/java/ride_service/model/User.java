package ride_service.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users") // Avoid using reserved keywords
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String role; // User, Driver

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
