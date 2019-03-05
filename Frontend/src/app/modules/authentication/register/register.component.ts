import { AuthenticationService} from '../authentication.service';
import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  newUser: User;
  constructor(private authService: AuthenticationService, private router: Router) {
    this.newUser = new User();
  }

  ngOnInit() {
  }

  registerUser() {
    console.log('Register User', this.newUser.userId, this.newUser.firstName);
    this.authService.registerUser(this.newUser).subscribe((data) => {
      console.log('user data', data);
      this.router.navigate(['/login']);
    });
  }

  resetInput() {
    this.newUser = new User();
  }
}
