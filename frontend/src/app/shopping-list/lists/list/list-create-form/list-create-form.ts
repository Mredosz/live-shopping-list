import { Component, inject, output, viewChild, viewChildren } from '@angular/core';
import { ListsService } from '../../lists-service';
import { Input } from '../../../../shared/input/input';
import { FormsModule, NgForm } from '@angular/forms';

@Component({
  selector: 'app-list-create-form',
  imports: [Input, FormsModule],
  templateUrl: './list-create-form.html',
  standalone: true,
})
export class ListCreateForm {
  private readonly listService = inject(ListsService);
  private readonly inputComponents = viewChildren(Input);
  private readonly form = viewChild(NgForm);
  closeForm = output();

  createList() {
    const components = this.inputComponents();

    const title = components[0].value();
    this.listService.createList({ title, participantUsernames: [''] }).subscribe({
      next: () => {
        this.closeForm.emit();
      },
    });

    this.form()?.resetForm();
  }
}
