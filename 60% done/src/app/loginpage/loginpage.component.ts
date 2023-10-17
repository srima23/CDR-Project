import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-loginpage',
  templateUrl: './loginpage.component.html',
  styleUrls: ['./loginpage.component.css']
})
export class LoginpageComponent {
  username: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) { }

  login(): void {
    console.log("hey script");
    this.authService.login(this.username, this.password).subscribe(
      (response) => {
        const token = response.token; // if login successful then receive a token  Assuming the token is returned as part of the response
        this.authService.setToken(token); // extracted token is set 

        // Redirect to the "/home" page
        this.router.navigate(['/home']);
        alert('You have successfully logged in!');
      },
      (error) => {
        console.error('Login failed:', error);
      }
    );
  }

}
