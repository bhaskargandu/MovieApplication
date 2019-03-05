import { Component, OnInit } from '@angular/core';
import {User} from '../user';
import {AuthenticationService} from '../authentication.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User;
  constructor(private authService: AuthenticationService, private router: Router) { }

  ngOnInit() {
    this.user = new User();
  }

  loginUser() {
    console.log('Register User', this.user.userId, this.user.password);

    this.authService.loginUser(this.user).subscribe(data => {
      console.log('Logged in!!!');
      if (data['token']) {
        this.authService.setToken(data['token']);
        console.log('token', data['token']);
        this.router.navigate(['/movies/popular']);
      }
    });
  }
}
