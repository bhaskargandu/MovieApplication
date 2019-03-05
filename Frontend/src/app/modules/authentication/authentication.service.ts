import {User} from './user';
import {Observable} from 'rxjs';
import {map, retry} from 'rxjs/operators';
import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import * as jwt_decode from 'jwt-decode';

export const TOKEN_NAME = 'jwt_token';

@Injectable()
export class AuthenticationService {

  springEndPoint: string;
  token: string;

  constructor(private http: HttpClient) {
    this.springEndPoint = 'http://localhost:8089/api/v1/userservice';
  }

  registerUser(newUser: User) {
    const url = this.springEndPoint + '/register';
    return this.http.post(url, newUser, { responseType: 'text' });
  }

  loginUser(user) {
    const url = this.springEndPoint + '/login';
    return this.http.post(url, user);
  }

  setToken(token: string) {
    return localStorage.setItem(TOKEN_NAME, token);
  }

  getToken() {
    return localStorage.getItem(TOKEN_NAME);
  }

  deleteToken() {
    return localStorage.removeItem(TOKEN_NAME);
  }

  getTokenExpirationDate(token: string): Date {
    const decode = jwt_decode(token);
    if (decode.exp === undefined) {
      return null;
    }
    const date = new Date(0);
    date.setUTCSeconds(decode.exp);
    return date;
  }

  isTokenExpired(token?: string): boolean {
    if (!token) {
      token = this.getToken();
    }
    if (!token) {
      return true;
    }
    const date = this.getTokenExpirationDate(token);
    if (date === undefined || date === null) {
      return false;
    }
    return !(date.valueOf() > new Date().valueOf());
  }
}
