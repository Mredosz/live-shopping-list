import { Component, output } from '@angular/core';

@Component({
  selector: 'app-modal',
  imports: [],
  templateUrl: './modal.html',
  standalone: true,
})
export class Modal {
  closeModal = output();
}
