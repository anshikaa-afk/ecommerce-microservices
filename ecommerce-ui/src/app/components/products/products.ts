import { Component, inject, OnInit, NgZone} from '@angular/core';
import { Product } from '../../models/product';
import { ProductService } from '../../services/product';
import { CommonModule,  JsonPipe} from '@angular/common';


@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule, JsonPipe],
  templateUrl: './products.html',
  styleUrl: './products.css',
})

export class ProductsComponent implements OnInit {
  
  private productService = inject(ProductService);
  products: Product[] = [];
  private zone = inject(NgZone);

  ngOnInit(): void {
     console.log("ProductsComponent initialized");
     setInterval(() => {
    console.log('Current length =', this.products.length);
  }, 2000);

     this.loadProducts();
  }

  // loadProducts() {
  //     console.log("Calling Product Service...");
  //     this.productService.getProducts().subscribe({
  //     next: (response) => {
  //       this.products = response;
  //       console.log('Products loaded successfully:', this.products);
  //     }, 
  //     error: (error) => {
  //       console.error('Error loading products:', error);
  //     }
  //   });
  // }

  loadProducts() {

    this.productService.getProducts().subscribe({

      next: (response) => {

  console.log("Before zone:", NgZone.isInAngularZone());

  this.zone.run(() => {

    console.log("Inside zone.run:", NgZone.isInAngularZone());

    this.products = response;

  });

}
});

  }
}
