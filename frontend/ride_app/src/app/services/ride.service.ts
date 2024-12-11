import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RideRequest } from '../model/ride-request';
import { Driver } from '../model/driver';
import { AvailableDriverResponse } from '../model/availableDrivers/available-driver-response';
import { NotifyDriverRequest } from '../model/availableDrivers/notify-driver-request';
import { NotifyUserRequest } from '../model/availableDrivers/notify-user-request';

@Injectable({
  providedIn: 'root'
})
export class RideService {
  private apiUrl = 'http://localhost:8082/drivers/nearby-available-drivers'; // Update with your API URL

  constructor(private http: HttpClient) {}

  notifyListOfDrivers(notifyDriverRequest: NotifyDriverRequest): Observable<string> {
    return this.http.post<string>('http://localhost:8082/driver/notifyDrivers', notifyDriverRequest);
  }

  notifyUser(notifyUserRequest: NotifyUserRequest): Observable<string> {
    return this.http.post<string>('http://localhost:8082/driver/notifyUser', notifyUserRequest);
  }

  createRide(rideDetails: RideRequest): Observable<AvailableDriverResponse> {
    return this.http.post<AvailableDriverResponse>(this.apiUrl, rideDetails);
  }



}
