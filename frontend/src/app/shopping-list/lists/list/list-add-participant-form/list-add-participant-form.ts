import { Component, inject, input, output, viewChild, viewChildren } from '@angular/core';
import { ListsService } from '../../lists-service';
import { FormsModule, NgForm } from '@angular/forms';
import { Input } from '../../../../shared/input/input';

@Component({
  selector: 'app-list-add-participant-form',
  imports: [FormsModule, Input],
  templateUrl: './list-add-participant-form.html',
  standalone: true,
})
export class ListAddParticipantForm {
  private readonly listService = inject(ListsService);

  listId = input.required<string>();
  closeForm = output();

  private readonly inputComponents = viewChildren(Input);
  private readonly form = viewChild(NgForm);

  addParticipant() {
    const components = this.inputComponents();

    const username = components[0].value();

    this.listService.addParticipant(this.listId(), username).subscribe({
      next: () => {
        this.closeForm.emit();
      },
    });

    this.form()?.resetForm();
  }
}
