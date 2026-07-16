import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './products.html',
  styleUrl: './products.css'
})
export class ProductsComponent implements OnInit {

  private http = inject(HttpClient);

  products: any[] = [];

  ngOnInit(): void {

    console.log("Calling Backend...");

    this.http.get<any[]>("http://localhost:8081/api/products")
      .subscribe({

        next: (response) => {

          console.log("Response:", response);

          this.products = response;

          console.log("Products Assigned:", this.products);

        },

        error: console.error

      });

  }

}