package com.cibertec.marketvirtual.Controller;

import com.cibertec.marketvirtual.DTO.OrdenesEstadoHistorialDTO;
import com.cibertec.marketvirtual.Service.OrdenesEstadoHistorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes/{ordenId}/historial-estados")
public class OrdenesEstadoHistorialController {

    @Autowired
    private OrdenesEstadoHistorialService historialService;

    /**
     * Endpoint para listar el historial de estados de una orden por su ID.
     *
     * @param ordenId ID de la orden.
     * @return Lista de DTOs del historial de estados.
     */
    @GetMapping
    public ResponseEntity<List<OrdenesEstadoHistorialDTO>> listarHistorialPorOrdenId(@PathVariable Long ordenId) {
        List<OrdenesEstadoHistorialDTO> historial = historialService.listarHistorialPorOrdenId(ordenId);
        return ResponseEntity.ok(historial);
    }
}
