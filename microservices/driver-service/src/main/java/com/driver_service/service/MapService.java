package com.driver_service.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MapService {

    public String getRandomLocation() {
        Random random = new Random();
        double latitude = 37.7749 + (random.nextDouble() - 0.5);  // Mock latitude
        double longitude = -122.4194 + (random.nextDouble() - 0.5);  // Mock longitude
        return latitude + "," + longitude;  // Return location as a string (lat,long)
    }
}
