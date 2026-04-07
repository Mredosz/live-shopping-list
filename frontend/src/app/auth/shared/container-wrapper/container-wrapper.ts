import { Component, input, output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-container-wrapper',
  imports: [FormsModule, RouterLink],
  templateUrl: './container-wrapper.html',
  standalone: true,
})
export class ContainerWrapper {
  buttonLabel = input.required<string>();
  header = input.required<string>();
  navLink = input.required<string>();
  navLinkLabel = input.required<string>();
  submitForm = output();
}
