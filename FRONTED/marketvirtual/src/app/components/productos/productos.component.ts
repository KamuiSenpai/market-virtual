import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html',
  styleUrls: ['./productos.component.css'], // Corregido: styleUrls en plural
})
export class ProductosComponent implements OnInit {
  productos: any[] = []; // Puedes usar un modelo específico si lo tienes definido

  ngOnInit(): void {
    // Aquí asignamos datos estáticos de ejemplo a los productos
    this.productos = [
      {
        id: 1,
        nombre: 'Producto 1',
        descripcion: 'Descripción del Producto 1',
        precio: 10.0,
        categoria: 'Categoría 1',
      },
      {
        id: 2,
        nombre: 'Producto 2',
        descripcion: 'Descripción del Producto 2',
        precio: 20.0,
        categoria: 'Categoría 2',
      },
      {
        id: 3,
        nombre: 'Producto 3',
        descripcion: 'Descripción del Producto 3',
        precio: 30.0,
        categoria: 'Categoría 3',
      },
    ];
  }
}
