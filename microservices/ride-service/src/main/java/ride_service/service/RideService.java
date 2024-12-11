package ride_service.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import ride_service.model.Driver;
import ride_service.model.Ride;
import ride_service.model.User;
import ride_service.repository.RideRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class RideService {

//    @Autowired
//    public RestTemplate restTemplate;

    @Autowired
    private RideRepository rideRepository;

    private final RestTemplate restTemplate;

    public RideService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Create a new ride
    public Ride createRide(User user, String pickupLocation, String dropLocation) {
        Ride ride = new Ride();
        ride.setUser(user);
        ride.setPickupLocation(pickupLocation);
        ride.setDropLocation(dropLocation);
        ride.setStatus("PENDING");  // Initially, the ride status is PENDING
        ride.setCreatedAt(LocalDateTime.now());
        ride.setUpdatedAt(LocalDateTime.now());
        return rideRepository.save(ride);
    }

    // Get available drivers for a ride
    public List<Driver> getAvailableDriversOLD(String pickupLocation) {
        // For simplicity, assume all drivers are available.
        // Ideally, you would call the Driver service to get drivers near the pickup location.
        return List.of(new Driver(1L, "SUV", "AVAILABLE", "37.7749,-122.4194", 4.5),
                new Driver(2L, "Sedan", "AVAILABLE", "37.7750,-122.4183", 4.7));
    }


    public List<Driver> getAvailableDrivers(String pickupLocation) {
        String url = "http://localhost:8082/drivers/available";

        // Make the REST call and map the response to Driver[]
        Driver[] drivers = this.restTemplate.getForObject(url, Driver[].class);

        // Convert the array to a List and return
        return Arrays.asList(drivers);
    }

//    public List<Driver> getAvailableDrivers(String pickupLocation) {
//        // Call the external Driver service API to get available drivers
//        List<Driver> drivers = webClient.get()
//                .uri(uriBuilder -> uriBuilder
//                        .path("/drivers/available")
//                        .queryParam("pickupLocation", pickupLocation)
//                        .build())
//                .retrieve()
//                .bodyToFlux(Driver.class) // Expecting a list of drivers
//                .collectList()
//                .block(); // Blocking to wait for the response
//
//        return drivers;
//    }

    // Notify driver about the ride
    public void notifyDriver(Long driverId, Long rideId) {
        // You would implement a notification mechanism here, like sending an email or SMS
        // For now, we will just print a message
        System.out.println("Driver " + driverId + " notified about ride " + rideId);
    }

    // Assign a driver to a ride
    public Ride assignDriver(Long rideId, Long driverId) {
        Optional<Ride> rideOptional = rideRepository.findById(rideId);
        if (rideOptional.isPresent()) {
            Ride ride = rideOptional.get();
            ride.setDriver(new Driver(driverId, "SUV", "AVAILABLE", "37.7749,-122.4194", 4.5)); // Get Driver details from Driver Service
            ride.setStatus("ASSIGNED");
            ride.setUpdatedAt(LocalDateTime.now());
            return rideRepository.save(ride);
        }
        return null;
    }

    // Update ride status (e.g., STARTED, COMPLETED, CANCELLED)
    public Ride updateRideStatus(Long rideId, String status) {
        Optional<Ride> rideOptional = rideRepository.findById(rideId);
        if (rideOptional.isPresent()) {
            Ride ride = rideOptional.get();
            ride.setStatus(status);
            ride.setUpdatedAt(LocalDateTime.now());
            return rideRepository.save(ride);
        }
        return null;
    }

    // Get all rides for a user
    public List<Ride> getRidesByUser(Long userId) {
        return rideRepository.findByUserId(userId);
    }

    // Get ride details
    public Optional<Ride> getRideDetails(Long rideId) {
        return rideRepository.findById(rideId);
    }

    // Cancel a ride
    public Ride cancelRide(Long rideId) {
        Optional<Ride> rideOptional = rideRepository.findById(rideId);
        if (rideOptional.isPresent()) {
            Ride ride = rideOptional.get();
            ride.setStatus("CANCELLED");
            ride.setUpdatedAt(LocalDateTime.now());
            return rideRepository.save(ride);
        }
        return null;
    }
}
