import { Injectable } from '@angular/core';
import { CartItem } from '../../shared/models/cart-item.model';
import { Product } from '../../shared/models/product.model';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private items: CartItem[] = [];

  getItems(): CartItem[] {
    return this.items;
  }

  addToCart(product: Product): void {

    const item = this.items.find(
      i => i.product.id === product.id
    );

    if(item){
      item.quantity++;
    }else{
      this.items.push({
        product,
        quantity:1
      });
    }
  }
}