import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { UserResponse } from '../model/user-response';
import { WebSocketService } from '../services/web-socket.service';
import { NotifyDriverRequest } from '../model/availableDrivers/notify-driver-request';
import { RideService } from '../services/ride.service';
import { NotifyUserRequest } from '../model/availableDrivers/notify-user-request';

@Component({
  selector: 'app-driver-dashboard',
  standalone: false,
  templateUrl: './driver-dashboard.component.html',
  styleUrls: ['./driver-dashboard.component.css']
})
export class DriverDashboardComponent {

  @Input() driver!: UserResponse;

  userRideRequests: NotifyDriverRequest[] = [];

  // driverId: number = 5; // Example driver ID. This will be dynamically set for each driver
  // rideRequest: any;

  constructor(private webSocketService: WebSocketService, private rideService: RideService) {}

  ngOnInit(): void {
    // Connect to the WebSocket for the specific driver

    this.webSocketService.subscribeToDriverNotifications(this.driver.id, message=>{
      console.log("some user request for ride")
      console.log(message)
      this.userRideRequests.push(message);
    });

  }

  acceptRide(userRideRequest: NotifyDriverRequest){

    console.log("acceptRide")
    console.log(userRideRequest)

    let notifyUserRequest = new NotifyUserRequest();
    notifyUserRequest.userId = userRideRequest.userId;
    notifyUserRequest.rideId = "458656"
    notifyUserRequest.response = "OK";
    if(userRideRequest !== undefined && userRideRequest?.driverIds){
      notifyUserRequest.driverId = userRideRequest.driverIds[0]
    }
    this.rideService.notifyUser(notifyUserRequest)
    .subscribe(response=>{
      console.log(response);
    })
   
  }
  
  // rejectRide(userRideRequest: NotifyDriverRequest){
  //   this.userRideRequests
  // }

  // ngOnDestroy(): void {
  //   // Disconnect from WebSocket when the component is destroyed
  //   this.webSocketService.disconnect();
  // }
}
