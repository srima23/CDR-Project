// authService.ts - Angular service for handling authentication

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth/token';

  constructor(private http: HttpClient, private router: Router) { }

  login(username: string, password: string): Observable<any> {
    console.log("service");
    const body = { username, password };
    return this.http.post(`${this.apiUrl}`, body);
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }

  setToken(token: string): void {
    localStorage.setItem('token', token);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  getHeaders(): HttpHeaders {
    const token = this.getToken();
    return new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`,
    });
  }

  // getUserScope(): string | null { //extract users scope/role 
  //   const token = this.getToken();  //users role extract
  //   if (token) {  //if present
  //     // the token contains the user's role information in some way (e.g., as a claim)
  //     // You would need to parse the token and extract the role information based on your token structure
  //     // For simplicity, let's assume the token contains a field called 'role'
  //     const decodedToken = JSON.parse(atob(token.split('.')[1]));  //headder,payload,sign
  //     console.log(decodedToken.scope);
  //     return decodedToken.scope;//  if token structure==assumption ret users scope/role
  //   }
  //   return null;
}

