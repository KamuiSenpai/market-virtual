import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductosService {
  private apiUrl = 'http://localhost:8080/api/productos'; // Endpoint de tu API

  constructor(private http: HttpClient) {}

  getProductos(): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem('token')}` // Incluye el token si lo usas
    });
    return this.http.get(this.apiUrl, { headers });
  }
}
