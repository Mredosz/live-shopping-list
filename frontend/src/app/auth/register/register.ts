import { Component, inject } from '@angular/core';
import { AuthService } from '../auth-service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-register',
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './register.html',
  styles: ``,
})
export class Register {
  private readonly authService = inject(AuthService);

  onSubmit(username: string, password: string, confirmPassword: string) {
    this.authService.register(username, password, confirmPassword);
  }
}
