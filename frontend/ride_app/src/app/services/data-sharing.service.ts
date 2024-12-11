import { Injectable } from '@angular/core';
import { AvailableDriverResponse } from '../model/availableDrivers/available-driver-response';
import { Subject } from 'rxjs/internal/Subject';
import { RideRequest } from '../model/ride-request';

@Injectable({
  providedIn: 'root'
})
export class DataSharingService {

  public rideRequestSubject = new Subject<RideRequest>();
  public rideRequestObservable = this.rideRequestSubject.asObservable();


  private availableDriverResponse: AvailableDriverResponse | undefined;

  setAvailableDrivers(availableDriverResponse: AvailableDriverResponse) { 
    this.availableDriverResponse = availableDriverResponse; 
  }
  getAvailableDrivers(){
    return this.availableDriverResponse;
  }
  
  // Method to handle incoming ride request notifications
  notifyDriverOfNewRequest(request: RideRequest) {
    this.rideRequestSubject.next(request);
  }

  // Method to accept ride
  acceptRide(request: RideRequest) {
    // Logic to notify the backend that the ride has been accepted
  }

  // Method to reject ride
  rejectRide(request: RideRequest) {
    // Logic to notify the backend that the ride has been rejected
  }
}
