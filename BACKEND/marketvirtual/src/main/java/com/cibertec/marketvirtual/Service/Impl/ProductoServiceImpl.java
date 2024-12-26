package com.cibertec.marketvirtual.Service.Impl;

import com.cibertec.marketvirtual.Model.Producto;
import com.cibertec.marketvirtual.Repository.ProductoRepository;
import com.cibertec.marketvirtual.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    @Override
    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        Producto productoExistente = obtenerProductoPorId(id);
        productoExistente.setNombre(productoActualizado.getNombre());
        productoExistente.setDescripcion(productoActualizado.getDescripcion());
        productoExistente.setPrecio(productoActualizado.getPrecio());
        productoExistente.setCategoria(productoActualizado.getCategoria());
        productoExistente.setUnidadMedida(productoActualizado.getUnidadMedida());
        productoExistente.setActivo(productoActualizado.getActivo());
        productoExistente.setImagenUrl(productoActualizado.getImagenUrl());
        return productoRepository.save(productoExistente);
    }

    @Override
    public void eliminarProducto(Long id) {
        Producto productoExistente = obtenerProductoPorId(id);
        productoRepository.delete(productoExistente);
    }
}
