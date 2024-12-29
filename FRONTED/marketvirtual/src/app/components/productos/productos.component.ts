import { Component, OnInit } from '@angular/core';
import { ProductosService } from './../../services/productos.service';


@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html',
  styleUrls: ['./productos.component.css']
})
export class ProductosComponent implements OnInit {
  productos: any[] = []; // Declarar productos como un arreglo

  constructor(private productosService: ProductosService) {}

  ngOnInit(): void {
    this.cargarProductos();
  }

  cargarProductos(): void {
    this.productosService.getProductos().subscribe(
      (data: any[]) => {
        this.productos = data.map((producto) => ({
          nombre: producto.nombre,
          categoria: producto.categoriaNombre || 'Sin categoría',
          precio: producto.precio,
          unidad: producto.unidadMedidaNombre || 'Sin unidad',
          estado: producto.activo ? 'Activo' : 'Inactivo',
        }));
      },
      (error: any) => { // Especificar el tipo del parámetro
        console.error('Error al cargar los productos:', error);
      }
    );
  }


  editarProducto(producto: any): void {
    console.log('Editar producto:', producto);
  }

  eliminarProducto(id: number): void {
    console.log('Eliminar producto con ID:', id);
  }
}



