import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { WebSocketService } from '../services/web-socket.service';
import { UserResponse } from '../model/user-response';

@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  constructor(private authService: AuthService, private router: Router, private webSocketService: WebSocketService) {}
  ngOnInit(): void {
    this.webSocketService.connect();
  }

  onSubmit(event: Event) {
    event.preventDefault();
    const form = <HTMLFormElement>event.target;
    const username = form['username'].value;
    const password = form['password'].value;

    // Call the AuthService to handle the login request
    this.authService.login(username, password).subscribe(
      (response: UserResponse) => {
        // On success, navigate to the UserComponent
        console.log('Login successful:', response);

        // Store user data in localStorage
        this.authService.storeUserData(response);
        if(response.role == "user"){
          this.router.navigate(['/user']); // This assumes you have a route for UserComponent
        }
        else{
          this.router.navigate(['/driver']); 
        }
        
      },
      error => {
        // Handle error (e.g., invalid credentials)
        console.error('Login failed:', error);
        alert('Login failed! Please check your username and password.');
      }
    );
  }
}
