import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms'; // Importa FormsModule
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http'; // Agrega HTTP_INTERCEPTORS
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { PanelComponent } from './components/panel/panel.component';
import { ProductosComponent } from './components/productos/productos.component';
import { AuthInterceptor } from './interceptors/auth.interceptor'; // Importa el interceptor

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    PanelComponent,
    ProductosComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule, // Agrega FormsModule aquí
    HttpClientModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true } // Registra el interceptor
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }



