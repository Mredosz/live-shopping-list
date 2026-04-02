import { Component, inject, viewChildren } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../auth-service';
import { Input } from '../shered/input/input';
import { ContainerWrapper } from '../shered/container-wrapper/container-wrapper';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [FormsModule, Input, ContainerWrapper],
  templateUrl: './login.html',
  standalone: true,
})
export class Login {
  private readonly authService = inject(AuthService);
  private readonly inputComponents = viewChildren(Input);
  private readonly router = inject(Router);

  inputs = [
    {
      id: 'username',
      type: 'text',
      label: 'Nazwa użytkownika',
    },
    {
      id: 'password',
      type: 'password',
      label: 'Hasło',
    },
  ];

  onSubmit() {
    const components = this.inputComponents();

    const username = components[0].value();
    const password = components[1].value();

    this.authService.login(username, password);

    this.router.navigate(['/app']);
  }
}
