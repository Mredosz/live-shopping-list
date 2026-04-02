import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly httpClient = inject(HttpClient);

  login(username: string, password: string) {
    this.httpClient
      .post(
        'http://localhost:8080/auth/login',
        {
          username,
          password,
        },
        {
          withCredentials: true,
        },
      )
      .subscribe({
        error: (err) => console.error('Błąd', err),
      });
  }

  register(username: string, password: string, confirmPassword: string) {
    this.httpClient
      .post(
        'http://localhost:8080/auth/register',
        {
          username,
          password,
          confirmPassword,
        },
        {
          withCredentials: true,
        },
      )
      .subscribe({
        error: (err) => console.error('Błąd', err),
      });
  }

  logout() {
    this.httpClient
      .get('http://localhost:8080/auth/logout', {
        withCredentials: true,
      })
      .subscribe({
        error: (err) => console.error('Błąd', err),
      });
  }
}
