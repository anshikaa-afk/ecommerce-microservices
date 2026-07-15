import { Service } from '@angular/core';
import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root'
})


export class ProductService {

    private http = inject(HttpClient);
    private readonly API = environment.productServiceUrl+'/api/products';

    getProducts(): Observable<Product[]> {
        return this.http.get<Product[]>(this.API);
    }

}
