import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  credentials = {
    username: '',
    password: ''
  };

  constructor(private authService: AuthService, private router: Router) {}

  onLogin(): void {
    this.authService.login(this.credentials).subscribe({
      next: (response) => {
        // Guardar el token en localStorage
        localStorage.setItem('token', response.token);

        // Redirigir al panel
        this.router.navigate(['/panel']);
      },
      error: (error) => {
        console.error('Error al iniciar sesión', error);
        alert('Credenciales incorrectas');
      }
    });
  }
}
