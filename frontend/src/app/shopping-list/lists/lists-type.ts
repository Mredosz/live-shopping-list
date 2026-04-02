export interface ShoppingListDto {
  id: string;
  createdAt: string;
  title: string;
  items: ShoppingItemDto[];
}

export interface ShoppingListPageDto {
  items: ShoppingListDto[];
  hasNext: boolean;
}

export interface ShoppingItemDto {
  name: string;
  quantity: number;
  checked: boolean;
}
