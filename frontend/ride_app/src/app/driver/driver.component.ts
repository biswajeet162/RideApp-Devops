import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { UserResponse } from '../model/user-response';
import { WebSocketService } from '../services/web-socket.service';

@Component({
  selector: 'app-driver',
  standalone: false,
  
  templateUrl: './driver.component.html',
  styleUrl: './driver.component.css'
})
export class DriverComponent implements OnInit {

  driver!: UserResponse;


  constructor(private authService: AuthService, private webSocketService: WebSocketService){}

  ngOnInit(): void {
    this.webSocketService.connect();

    // Get user data from localStorage or AuthService
    this.driver = this.authService.getUserData();
  }

}
