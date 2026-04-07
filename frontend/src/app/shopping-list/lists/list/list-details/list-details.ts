import { Component, inject, input, OnInit, signal } from '@angular/core';
import { ListsService } from '../../lists-service';
import { ShoppingListDetailsDto } from '../../lists-type';
import { ListItem } from '../list-item/list-item';
import { AddListItem } from '../add-list-item/add-list-item';
import { Modal } from '../../../../shared/modal/modal';
import { ListAddParticipantForm } from '../list-add-participant-form/list-add-participant-form';

type ModalState = 'NONE' | 'ADD_ITEM' | 'ADD_PARTICIPANT';

@Component({
  selector: 'app-list-details',
  imports: [ListItem, AddListItem, Modal, ListAddParticipantForm],
  templateUrl: './list-details.html',
  standalone: true,
})
export class ListDetails implements OnInit {
  private readonly listsService = inject(ListsService);
  id = input.required<string>();
  list = signal<ShoppingListDetailsDto>({ createdAt: '', id: '', items: [], title: '' });

  activeModal = signal<ModalState>('NONE');

  ngOnInit(): void {
    this.refresh();
  }

  openModal(type: ModalState) {
    this.activeModal.set(type);
  }

  closeModal() {
    this.activeModal.set('NONE');
  }

  handleCloseAndRefresh() {
    this.closeModal();
    this.refresh();
  }

  private refresh() {
    this.listsService
      .fetchListDetails(this.id())
      .subscribe({ next: (list) => this.list.set(list) });
  }
}
