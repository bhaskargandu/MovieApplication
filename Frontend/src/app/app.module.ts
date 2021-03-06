import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';

import { AppComponent } from './app.component';
import { MovieModule } from './modules/movie/movie.module';
import {AuthenticationModule} from './modules/authentication/authentication.module';
import {AuthGuardService} from './authGuard.service';


const appRoutes: Routes = [
  // {
  //   path: '',
  //   redirectTo: 'movies',
  //   pathMatch: 'full',
  // }
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full',
  }
]

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    MovieModule,
    AuthenticationModule,
    BrowserModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatDialogModule,
    MatInputModule,
    RouterModule.forRoot(appRoutes),
  ],
  providers: [AuthGuardService],
  bootstrap: [AppComponent]
})
export class AppModule { }
