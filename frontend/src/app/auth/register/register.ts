import { Component, inject, viewChildren } from '@angular/core';
import { AuthService } from '../auth-service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ContainerWrapper } from '../shared/container-wrapper/container-wrapper';
import { Input } from '../../shared/input/input';

@Component({
  selector: 'app-register',
  imports: [FormsModule, ReactiveFormsModule, Input, ContainerWrapper, ContainerWrapper, Input],
  templateUrl: './register.html',
  standalone: true,
})
export class Register {
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
    {
      id: 'confirm-password',
      type: 'password',
      label: 'Powtórz hasło',
    },
  ];

  onSubmit() {
    const components = this.inputComponents();

    const username = components[0].value();
    const password = components[1].value();
    const confirmPassword = components[2].value();
    this.authService.register(username, password, confirmPassword);

    this.router.navigate(['/login']);
  }
}
