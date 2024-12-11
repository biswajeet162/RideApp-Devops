import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

// Define UserResponseDto model
export interface UserResponseDto {
  id: number;
  name: string;
  email: string;
  role: string;
  phoneNumber: string;
}

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:8081/users/login?'; // Replace with your backend API URL

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<UserResponseDto> {
    const body = { };
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.post<UserResponseDto>(this.apiUrl + "email="+ username +"&password=" + password, body, { headers });
  }
}
