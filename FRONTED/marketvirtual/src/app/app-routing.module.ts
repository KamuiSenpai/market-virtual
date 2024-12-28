import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { PanelComponent } from './components/panel/panel.component';
import { ProductosComponent } from './components/productos/productos.component';


const routes: Routes = [
  { path: 'login', component: LoginComponent }, // Ruta para login
  { path: 'panel', component: PanelComponent }, // Ruta para el panel
  { path: 'productos', component: ProductosComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' } // Redirección inicial

];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
