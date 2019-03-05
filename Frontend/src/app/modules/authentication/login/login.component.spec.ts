import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginComponent } from './login.component';
import {AuthenticationService} from '../authentication.service';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {MatButtonModule, MatFormFieldModule, MatInputModule} from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {RouterTestingModule} from '@angular/router/testing';
import {By} from '@angular/platform-browser';

const testConfig = {
  userData: {
    firstName: 'test',
    lastName: 'testLast',
    userId: 'testUser',
    password: 'testPass'
  }
};

describe('LoginComponent', () => {
  let loginComponent: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let authService: AuthenticationService;
  let spyUser: any;
  let routes: Router;
  let location: Location;

  class AuthServiceStub {
    currentUser: any;
    constructor() {};
    login(credentials) {
      if (credentials.userId === testConfig.userData.userId){
        return Observable.create(credentials.userId);
      }
      return Observable.create(false);
    }
  }

  class dummy {};

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginComponent ],
      imports: [FormsModule, HttpClientModule, MatFormFieldModule,
        MatInputModule, MatButtonModule, BrowserAnimationsModule, RouterTestingModule.withRoutes(
          [{path: '', component: dummy}])],
      providers: [{provider: AuthenticationService, useClass: AuthServiceStub}]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    routes = TestBed.get(Router);
    fixture = TestBed.createComponent(LoginComponent);
    location = TestBed.get(Location);
    loginComponent = fixture.componentInstance;
    fixture.detectChanges();
    fixture.debugElement.injector.get(AuthenticationService);
  });

  it('should create the app component', async (() => {
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));


  it('should contain two input box for userId and password', function () {
    const userId = fixture.debugElement.query(By.css('.inputID'));
    const password = fixture.debugElement.query(By.css('.inputID'));
    const registerButton = fixture.debugElement.query(By.css('.inputID'));
    const userButton = fixture.debugElement.query(By.css('.inputID'));

    const userIdInput = userId.nativeElement;
    const passwordInput = password.nativeElement;
    const registerButtonInput = registerButton.nativeElement;
    const userButtonInput = userButton.nativeElement;

    expect(userIdInput).toBeTruthy();
    expect(passwordInput).toBeTruthy();
    expect(registerButtonInput).toBeTruthy();
    expect(userButtonInput).toBeTruthy();
  });


});
