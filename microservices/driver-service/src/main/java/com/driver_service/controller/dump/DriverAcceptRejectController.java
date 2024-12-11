package com.driver_service.controller.dump;

import com.driver_service.model.RideRequest;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverAcceptRejectController {

    @MessageMapping("/rideRequest/{driverId}")
    @SendTo("/topic/rideRequest/{driverId}")
    public RideRequest getRideRequestForDriver(@PathVariable Long driverId, RideRequest rideRequest) {
        // Handle the specific driver request (this will be a real-time notification for that driver)
        return rideRequest;
    }
}

//
//import com.driver_service.model.websocket.WebSocketDriverResponse;
//import com.driver_service.model.websocket.WebSocketRideRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/ride")
//public class WebSocketRideController {
//
//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;
//
//    // Endpoint to notify selected drivers about a ride request
//    @PostMapping("/notifyDrivers")
//    public void notifyDrivers(@RequestBody WebSocketRideRequest rideRequest) {
//        List<Integer> driverIds = rideRequest.getDriverIds();
//        for (Integer driverId : driverIds) {
//            messagingTemplate.convertAndSend("/topic/driver-" + driverId, rideRequest);
//        }
//    }
//
//    // Endpoint to handle driver responses
//    @PostMapping("/driverResponse")
//    public void driverResponse(@RequestBody WebSocketDriverResponse driverResponse) {
//        messagingTemplate.convertAndSend("/topic/user-" + driverResponse.getRideId(), driverResponse);
//    }
//}
