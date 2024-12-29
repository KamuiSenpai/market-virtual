import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class ApiService {
  constructor(private http: HttpClient) {}

  get(endpoint: string): Observable<any> {
    return this.http.get(`${environment.apiUrl}${endpoint}`);
  }

  post(endpoint: string, data: any): Observable<any> {
    return this.http.post(`${environment.apiUrl}${endpoint}`, data);
  }

  put(endpoint: string, data: any): Observable<any> {
    return this.http.put(`${environment.apiUrl}${endpoint}`, data);
  }

  delete(endpoint: string): Observable<any> {
    return this.http.delete(`${environment.apiUrl}${endpoint}`);
  }
}
