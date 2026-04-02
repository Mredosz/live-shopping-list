import { Component, inject, input } from '@angular/core';
import { ShoppingListDto } from '../lists-type';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-list',
  imports: [],
  templateUrl: './list.html',
  standalone: true,
})
export class List {
  list = input.required<ShoppingListDto>();
  private readonly router = inject(Router);
  private readonly route = inject(ActivatedRoute);

  navigate() {
    this.router.navigate([this.list().id], { relativeTo: this.route });
  }
}
