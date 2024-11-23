package com.driver_service.repository;

import com.driver_service.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    // Fetch drivers by their status (e.g., "AVAILABLE")
    List<Driver> findAllByStatus(String status);
    Driver findByUserId(Long userId);
}

