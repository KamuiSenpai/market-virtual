package com.cibertec.marketvirtual.Service;

import com.cibertec.marketvirtual.Model.Categoria;

import java.util.List;

public interface CategoriaService {
    List<Categoria> listarCategorias();
    Categoria obtenerCategoriaPorId(Long id);
    Categoria agregarCategoria(Categoria categoria);
    Categoria actualizarCategoria(Long id, Categoria categoria);
    void eliminarCategoria(Long id);
}
