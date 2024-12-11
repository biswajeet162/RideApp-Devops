import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService, UserResponseDto } from '../services/auth.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  standalone: true,  // Declare it as standalone
  imports: [FormsModule],  // Import FormsModule directly here
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  onLogin(event: Event): void {
    event.preventDefault(); // Prevent form default submission

    console.log("this.username and pass")
    console.log(this.username)
    console.log(this.password)

    if (this.username && this.password) {
      this.authService.login(this.username, this.password).subscribe(
        (response: UserResponseDto) => {
          console.log('Login successful:', response);

          // Store the user data (for example, in localStorage)
          localStorage.setItem('user', JSON.stringify(response));

          // Navigate to the user component (replace with the actual route path)
          this.router.navigate(['/user']);
        },
        (error) => {
          console.error('Login failed:', error);
          // Handle error (e.g., show an error message to the user)
        }
      );
    } else {
      console.error('Please enter both username and password.');
    }
  }
}
