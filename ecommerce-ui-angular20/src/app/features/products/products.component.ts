import { Component, inject, OnInit } from '@angular/core';
import { CommonModule, CurrencyPipe } from '@angular/common';
import { ProductService } from '../../core/services/product.service';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import {MatChipsModule} from '@angular/material/chips';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule, CurrencyPipe, MatCardModule, MatButtonModule, MatIconModule, MatChipsModule],
  templateUrl: './products.html',
  styleUrl: './products.css'
})

export class ProductsComponent implements OnInit {

  private productService = inject(ProductService);

  products: any[] = [];

  ngOnInit(): void {

    this.productService.getProducts().subscribe({

        next: (response) => {

          this.products = response;

        },

        error: console.error

      });

  }

}