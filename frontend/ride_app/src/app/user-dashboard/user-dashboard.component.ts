import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DataSharingService } from '../services/data-sharing.service';
import { AvailableDriverResponse } from '../model/availableDrivers/available-driver-response';
import { VehicleType } from '../model/availableDrivers/vehicle-type';
import { DriverInfo } from '../model/availableDrivers/driver-info';
import { WebSocketService } from '../services/web-socket.service';
import { RideService } from '../services/ride.service';
import { NotifyDriverRequest } from '../model/availableDrivers/notify-driver-request';
import { UserResponse } from '../model/user-response';
import { AuthService } from '../services/auth.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user-dashboard',
  standalone: false,
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent implements OnInit {

  selectedDrivers!: VehicleType;

  userId!: string;
  location: string | undefined;
  availableDriverResponse: AvailableDriverResponse | undefined;

  constructor(
    private dataSharingService: DataSharingService,
    private http: HttpClient,
    private webSocketService: WebSocketService,
    private rideService: RideService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {

    this.route.queryParams.subscribe(params => {
      this.location = params['location'];
      this.userId = params['userId'];
      console.log('Location:', this.location, 'UserId:', this.userId);
    });

    this.availableDriverResponse = this.dataSharingService.getAvailableDrivers();

    this.webSocketService.subscribeToUserNotifications(this.userId, message=>{
      console.log("some driver accepted your request")
      console.log(message)
    });

  }

  // Method to handle driver selection (select multiple drivers)
  selectDriver(driver: VehicleType): void {
    this.selectedDrivers = driver;
    console.log("this.selectedDrivers")
    console.log(this.selectedDrivers)
  }



  // Method to handle booking the selected drivers
  bookSelectedDriver(): void {
    if (this.selectedDrivers.drivers.length > 0) {
      // Prepare the payload

      let notifyDriverRequest = new NotifyDriverRequest();
      notifyDriverRequest.userId = this.userId
      notifyDriverRequest.pickupLocation =  this.location;
      notifyDriverRequest.driverIds = this.selectedDrivers.drivers
      .map(data => data.driverId)
      .filter((id): id is string => id !== undefined);

      // Send HTTP request to notify backend and trigger WebSocket notifications for selected drivers
      this.rideService.notifyListOfDrivers(notifyDriverRequest)
      .subscribe({
        next: (response) => {
          console.log('Backend notified successfully:', response);
          alert('Booking request sent to selected drivers.');
        },
        error: (error) => {
          console.error('Error notifying drivers:', error);
          alert('Failed to notify drivers. Please try again.');
        }
      });
    } else {
      alert('Please select at least one driver to proceed.');
    }
  }
}
