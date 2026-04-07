export interface ShoppingListDto {
  id: string;
  createdAt: string;
  title: string;
}

export interface ShoppingListPageDto {
  items: ShoppingListDto[];
  hasNext: boolean;
}

export interface ShoppingListDetailsDto {
  id: string;
  createdAt: string;
  title: string;
  items: ShoppingItemDto[];
}

export interface ShoppingItemDto {
  name: string;
  quantity: number;
  checked: boolean;
}

export interface ShoppingListFormDto {
  title: string;
  participantUsernames: string[];
}

export interface ShoppingItemFormDto {
  name: string;
  quantity: number;
}

export interface UpdateShoppingItemStatusDto {
  checked: boolean;
}
