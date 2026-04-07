import { Component, input, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-input',
  imports: [FormsModule],
  templateUrl: './input.html',
  standalone: true,
})
export class Input {
  label = input.required<string>();
  id = input.required<string>();
  type = input.required<string>();
  value = signal('');
}
