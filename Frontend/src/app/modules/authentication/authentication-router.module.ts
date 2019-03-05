import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import { RouterModule, Routes } from '@angular/router';
import {NgModule} from '@angular/core';


const authRoutes: Routes = [{
  path: '',
  children: [
    {
      path: '',
      redirectTo: '/login',
      pathMatch: 'full',
    },
    {
      path: 'register',
      component: RegisterComponent,
    },
    {
      path: 'login',
      component: LoginComponent,
    }
  ]
}]

@NgModule({
  imports: [
    RouterModule.forChild(authRoutes),
  ],
  exports: [
    RouterModule,
  ]
})
export class AuthenticationRouterModule {

}
