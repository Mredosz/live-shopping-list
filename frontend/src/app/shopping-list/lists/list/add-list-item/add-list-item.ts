import { Component, inject, input, output, viewChild, viewChildren } from '@angular/core';
import { ListsService } from '../../lists-service';
import { Input } from '../../../../shared/input/input';
import { FormsModule, NgForm } from '@angular/forms';

@Component({
  selector: 'app-add-list-item',
  imports: [Input, FormsModule],
  templateUrl: './add-list-item.html',
  standalone: true,
})
export class AddListItem {
  private readonly listService = inject(ListsService);
  listId = input.required<string>();
  closeForm = output();

  private readonly inputComponents = viewChildren(Input);
  private readonly form = viewChild(NgForm);

  inputs = [
    {
      id: 'name',
      type: 'text',
      label: 'Nazwa',
    },
    {
      id: 'quantity',
      type: 'number',
      label: 'Ilość',
    },
  ];

  addItem() {
    const components = this.inputComponents();

    const name = components[0].value();
    const quantity = Number(components[1].value());

    this.listService.addItem(this.listId(), { name, quantity }).subscribe({
      next: () => {
        this.closeForm.emit();
      },
    });

    this.form()?.resetForm();
  }
}
