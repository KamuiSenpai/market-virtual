import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = `${environment.apiUrl}${environment.endpoints.login}`; // URL del endpoint de login

  constructor(private http: HttpClient) {}

  login(credentials: { username: string; password: string }): Observable<any> {
    return this.http.post(this.apiUrl, {
      email: credentials.username,
      contrasena: credentials.password
    });
  }

  logout(): void {
    localStorage.removeItem('token'); // Elimina el token almacenado
  }

  isAuthenticated(): boolean {
    const token = localStorage.getItem('token');
    return !!token; // Devuelve true si el token existe
  }
}


