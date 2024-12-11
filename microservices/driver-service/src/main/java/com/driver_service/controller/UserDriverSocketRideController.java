package com.driver_service.controller;



import com.driver_service.model.websocket.RideAcceptanceSocketModel;
import com.driver_service.model.websocket.RideRequestSocketModel;
import com.driver_service.service.UserDriverSocketRideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/driver")
public class UserDriverSocketRideController {

    @Autowired
    private UserDriverSocketRideService userDriverSocketRideService;

    // Endpoint to handle the ride booking request and notify drivers
    @PostMapping("/notifyDrivers")
    public ResponseEntity<?> notifyDrivers(@RequestBody RideRequestSocketModel rideRequest) {
        // Notify the selected drivers
        userDriverSocketRideService.notifyDrivers(rideRequest);

        return ResponseEntity.ok("Ride request sent to drivers");
    }

    // Endpoint to handle the ride booking request and notify drivers
    @PostMapping("/notifyUser")
    public ResponseEntity<?> notifyUser(@RequestBody RideAcceptanceSocketModel acceptanceRequest) {
        // Notify the selected drivers
        userDriverSocketRideService.notifyUser(acceptanceRequest);

        return ResponseEntity.ok("Acceptance request sent to User");
    }

}
