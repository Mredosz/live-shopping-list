import { ChangeDetectorRef, Component, inject, input } from '@angular/core';
import { ShoppingItemDto } from '../../lists-type';
import { ListsService } from '../../lists-service';

@Component({
  selector: 'app-list-item',
  imports: [],
  templateUrl: './list-item.html',
  standalone: true,
})
export class ListItem {
  private readonly listsService = inject(ListsService);
  private readonly cdr = inject(ChangeDetectorRef);

  item = input.required<ShoppingItemDto>();
  listId = input.required<string>();

  check() {
    const targetStatus = !this.item().checked;

    this.listsService
      .updateItemStatus(this.listId(), this.item().name, { checked: targetStatus })
      .subscribe({
        next: () => {
          this.item().checked = targetStatus;
          this.cdr.detectChanges();
        },
      });
  }
}
