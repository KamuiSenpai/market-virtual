package com.cibertec.marketvirtual.Service;


import com.cibertec.marketvirtual.DTO.OrdenesEstadoHistorialDTO;

import java.util.List;

public interface OrdenesEstadoHistorialService {

    /**
     * Listar el historial de estados de una orden por su ID.
     *
     * @param ordenId ID de la orden.
     * @return Lista de objetos DTO representando el historial de estados.
     */
    List<OrdenesEstadoHistorialDTO> listarHistorialPorOrdenId(Long ordenId);
}
