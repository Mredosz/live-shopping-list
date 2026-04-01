import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly httpClient = inject(HttpClient);

  login(username: string, password: string) {
    this.httpClient
      .post('http://localhost:8080/auth/login', {
        username,
        password,
      })
      .subscribe({
        next: (res) => console.log('OK', res),
        error: (err) => console.error('Błąd', err),
      });
  }
}
