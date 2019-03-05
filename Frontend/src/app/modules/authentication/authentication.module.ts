import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import {MatIconModule} from '@angular/material';
import {MatFormFieldModule, MatIcon} from '@angular/material';
import {MatInputModule} from '@angular/material';
import {MatCardModule} from '@angular/material';

import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import {AuthenticationService} from './authentication.service';
import {AuthenticationRouterModule} from './authentication-router.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    AuthenticationRouterModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule
  ],
  declarations: [LoginComponent, RegisterComponent],
  providers: [
    AuthenticationService
  ],
  exports: [ RegisterComponent, LoginComponent ]
})
export class AuthenticationModule { }
