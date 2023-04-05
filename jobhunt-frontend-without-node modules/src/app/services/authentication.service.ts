import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import { baseServerUrl } from '../common/constants';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  constructor(private client: HttpClient) {}

  isLoggedIn(): boolean {
    const token: string|null = this.getToken();
    return token != undefined && token != null;
  }

  getToken(): string|null{
    const token = localStorage.getItem('token');
    return token;
  }

  getUsername(): string | null{
    const username  = localStorage.getItem('username');
    return username;
  }

  login(username: string, password: string): Observable<string> {
    const url = baseServerUrl + '/login';
    const requestData = { username, password };
    console.log('request data', JSON.stringify(requestData));
    const observable: Observable<string> = this.client.post(url, requestData, {
      responseType: 'text',
    });
    return observable;
  }



  register(username: string, password: string): Observable<void> {
    const url = baseServerUrl + '/register';
    const requestData = { username, password };
    console.log('request data', JSON.stringify(requestData));
    const observable: Observable<void> = this.client.post<void>(url, requestData);
    return observable;
  }




  logout() {
    localStorage.removeItem('username');
    localStorage.removeItem('token');
  }

  saveToken(username: string, token: string) {
    localStorage.setItem('username', username);
    localStorage.setItem('token', token);
  }
}
