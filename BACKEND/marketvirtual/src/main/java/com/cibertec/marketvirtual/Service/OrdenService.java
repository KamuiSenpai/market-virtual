package com.cibertec.marketvirtual.Service;

import com.cibertec.marketvirtual.DTO.OrdenDTO;
import com.cibertec.marketvirtual.Model.Orden;

import java.util.List;

public interface OrdenService {
    List<OrdenDTO> listarOrdenes(); // Devuelve DTOs para listar órdenes
    OrdenDTO obtenerOrdenPorId(Long id); // Devuelve un DTO para una orden específica
    Orden crearOrden(Orden orden); // Recibe y devuelve entidades para creación
    Orden actualizarOrden(Long id, Orden orden); // Recibe entidades para actualización
    void eliminarOrden(Long id); // Permite eliminar una orden
}
