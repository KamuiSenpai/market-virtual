package com.cibertec.marketvirtual.Controller;

import com.cibertec.marketvirtual.DTO.ProductoDTO;
import com.cibertec.marketvirtual.Model.Producto;
import com.cibertec.marketvirtual.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    /**
     * Listar todos los productos
     */
    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listarProductos() {
        List<ProductoDTO> productos = productoService.listarProductos()
                .stream()
                .map(ProductoDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productos);
    }

    /**
     * Obtener un producto por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerProductoPorId(@PathVariable Long id) {
        Producto producto = productoService.obtenerProductoPorId(id);
        return ResponseEntity.ok(new ProductoDTO(producto));
    }

    /**
     * Crear un nuevo producto
     */
    @PostMapping
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.crearProducto(producto);
        return ResponseEntity.ok(new ProductoDTO(nuevoProducto));
    }

    /**
     * Actualizar un producto por ID
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        Producto productoActualizado = productoService.actualizarProducto(id, producto);
        return ResponseEntity.ok(new ProductoDTO(productoActualizado));
    }

    /**
     * Eliminar un producto por ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
