package ride_service.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ride_service.model.Driver;
import ride_service.model.Ride;
import ride_service.model.RideRequest;
import ride_service.model.User;
import ride_service.service.RideService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rides")
public class RideController {

    @Autowired
    private RideService rideService;

    // Create a ride request
    @PostMapping("/create")
    public Ride createRide(@RequestBody RideRequest rideRequest) {
        // Assume you get the user from the authenticated session or token
        User user = new User(); // Example, populate user based on authenticated session
        return rideService.createRide(user, rideRequest.getPickupLocation(), rideRequest.getDropLocation());
    }

    // Get available drivers for the ride
    @GetMapping("/available-drivers")
    public List<Driver> getAvailableDrivers(@RequestParam String pickupLocation) {
        return rideService.getAvailableDrivers(pickupLocation);
    }

    // Notify driver about the ride
    @PostMapping("/drivers/{driverId}/notify-ride")
    public void notifyDriver(@PathVariable Long driverId, @RequestParam Long rideId) {
        rideService.notifyDriver(driverId, rideId);
    }

    // Assign driver to the ride
    @PostMapping("/{rideId}/assign-driver")
    public Ride assignDriver(@PathVariable Long rideId, @RequestParam Long driverId) {
        return rideService.assignDriver(rideId, driverId);
    }

    // Update the status of the ride
    @PostMapping("/{rideId}/update-status")
    public Ride updateRideStatus(@PathVariable Long rideId, @RequestParam String status) {
        return rideService.updateRideStatus(rideId, status);
    }

    // Get ride details
    @GetMapping("/{rideId}")
    public Optional<Ride> getRideDetails(@PathVariable Long rideId) {
        return rideService.getRideDetails(rideId);
    }

    // Get all rides for a user
    @GetMapping("/history/{userId}")
    public List<Ride> getRidesByUser(@PathVariable Long userId) {
        return rideService.getRidesByUser(userId);
    }

    // Cancel a ride
    @PostMapping("/{rideId}/cancel")
    public Ride cancelRide(@PathVariable Long rideId) {
        return rideService.cancelRide(rideId);
    }
}
