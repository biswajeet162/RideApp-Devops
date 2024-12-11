package com.driver_service.controller.dump;



import com.driver_service.model.websocket.RideRequestSocketModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
@Slf4j
public class DriverNotificationController {

    // Listen for ride requests and send notifications
    @MessageMapping("/rideRequest")
    @SendTo("/topic/rideRequest")
    public RideRequestSocketModel sendRideRequest(RideRequestSocketModel rideRequest) {
        // In a real app, you could handle further logic here, such as validating or saving the request
        log.info("DriverNotificationController");

        return rideRequest;
    }
}
