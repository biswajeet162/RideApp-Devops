package com.driver_service.service;



import com.driver_service.model.websocket.RideAcceptanceSocketModel;
import com.driver_service.model.websocket.RideRequestSocketModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class UserDriverSocketRideService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // This method will notify drivers via WebSocket
    public void notifyDrivers(RideRequestSocketModel rideRequest) {

        RideRequestSocketModel tempRideForDriver = new RideRequestSocketModel();
        tempRideForDriver.setUserId(rideRequest.getUserId());
        tempRideForDriver.setPickupLocation(rideRequest.getPickupLocation());

        // Iterate through the list of driver IDs and send them a message
        for (Long driverId : rideRequest.getDriverIds()) {
            // Send ride request notification to the specific driver
            ArrayList<Long> driverIdMain = new ArrayList<>();
            driverIdMain.add(driverId);
            tempRideForDriver.setDriverIds(driverIdMain);

            messagingTemplate.convertAndSend("/topic/driver/" + driverId, tempRideForDriver);
        }
    }

    public void notifyUser(RideAcceptanceSocketModel acceptanceRequest){
        messagingTemplate.convertAndSend("/topic/user/" + acceptanceRequest.getUserId(), acceptanceRequest);
    }
}
