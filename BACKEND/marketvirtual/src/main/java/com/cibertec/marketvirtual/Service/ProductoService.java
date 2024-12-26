package com.cibertec.marketvirtual.Service;

import com.cibertec.marketvirtual.Model.Producto;

import java.util.List;

public interface ProductoService {

    List<Producto> listarProductos();

    Producto obtenerProductoPorId(Long id);

    Producto crearProducto(Producto producto);

    Producto actualizarProducto(Long id, Producto producto);

    void eliminarProducto(Long id);
}
