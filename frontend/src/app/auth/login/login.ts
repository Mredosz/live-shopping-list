import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../auth-service';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.html',
})
export class Login {
  private readonly authService = inject(AuthService);

  onSubmit(username: string, password: string) {
    this.authService.login(username, password);
  }
}
