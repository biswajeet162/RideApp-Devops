package com.driver_service.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "app_user")
@Data
public class User {

    @Id
    private Long id; // Remove @GeneratedValue to accept manual ID values

    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String role;

    @Version
    private Integer version;  // This will handle optimistic locking

    // Getters and Setters
}
