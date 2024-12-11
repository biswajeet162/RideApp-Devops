import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { RideService } from '../services/ride.service';
import { UserResponse } from '../model/user-response';
import { RideRequest } from '../model/ride-request';
import { AvailableDriverResponse } from '../model/availableDrivers/available-driver-response';
import { DataSharingService } from '../services/data-sharing.service';
import { WebSocketService } from '../services/web-socket.service';

@Component({
  selector: 'app-user',
  standalone: false,
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  user!: UserResponse;
  originAddress: string = '';
  destinationAddress: string = '';
  vehicleType: string = '';

  constructor(private authService: AuthService, private rideService: RideService, private router: Router, private dataSharingService: DataSharingService, private webSocketService: WebSocketService) {}

  ngOnInit(): void {
    this.webSocketService.connect();
    // Get user data from localStorage or AuthService
    this.user = this.authService.getUserData();
    
  }

  // Handling form submission
  onSubmit(originInput: HTMLInputElement, destinationInput: HTMLInputElement, vehicleSelect: HTMLSelectElement): void {
    // Retrieve input values
    this.originAddress = originInput.value;
    this.destinationAddress = destinationInput.value;
    this.vehicleType = vehicleSelect.value;

    // Validation for empty fields
    if (this.originAddress && this.destinationAddress && this.vehicleType) {
      let ride = new RideRequest();
      ride.userId = this.user?.id;
      ride.pickupLocation = this.originAddress;
      ride.dropLocation = this.destinationAddress;
      ride.userCoordinates = "34, 23"
      // Proceed with backend call or routing
      // Call a service or API to calculate the ride details
      this.getAvailableDrivers(ride);
    } else {
      alert('Please fill all fields.');
    }
  }

  getAvailableDrivers(rideRequest: RideRequest): void {
    
    this.rideService.createRide(rideRequest).subscribe(
      (response: AvailableDriverResponse) => {
        console.log('Ride created successfully:', response);
        console.log('Ride created successfully:', response.userCoordinates);
        this.dataSharingService.setAvailableDrivers(response);
        this.router.navigate(['/user-dashboard'], { 
          queryParams: { userId: rideRequest.userId, pickupLocation: rideRequest.pickupLocation } 
        }); 
      },
      (error) => {
        console.error('Error creating ride:', error);
        alert('There was an error creating the ride.');
      }
    );
  }
  
}
