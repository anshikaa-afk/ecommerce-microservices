import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../../shared/models/product.model';

@Injectable({
  providedIn: 'root',
})

export class ProductService {

  private http = inject(HttpClient);

  private readonly API =
    'http://localhost:8081/api/products';

  getProducts(): Observable<Product[]> {

    return this.http.get<Product[]>(this.API);

  }

}
