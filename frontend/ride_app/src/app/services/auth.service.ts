import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserResponse } from '../model/user-response';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8081/users/login?'; // Replace with your backend URL

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<UserResponse> {
    const body = {  };
    return this.http.post<UserResponse>(this.apiUrl + "email=" + username +  "&password=" + password , body);
  }

  // Method to store user data in localStorage
  storeUserData(userResponse: any): void {
    localStorage.setItem('user', JSON.stringify(userResponse));
  }

  // Method to get user data from localStorage
  getUserData(): any {
    return JSON.parse(localStorage.getItem('user') || '{}');
  }

  // Method to clear user data from localStorage
  clearUserData(): void {
    localStorage.removeItem('user');
  }
}