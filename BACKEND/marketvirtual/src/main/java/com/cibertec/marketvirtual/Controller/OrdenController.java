package com.cibertec.marketvirtual.Controller;

import com.cibertec.marketvirtual.DTO.OrdenDTO;
import com.cibertec.marketvirtual.Model.Orden;
import com.cibertec.marketvirtual.Service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    /**
     * Listar todas las órdenes (Usando DTO para evitar recursividad).
     */
    @GetMapping
    public ResponseEntity<List<OrdenDTO>> listarOrdenes() {
        return ResponseEntity.ok(ordenService.listarOrdenes());
    }

    /**
     * Obtener una orden por ID (Usa la entidad directamente).
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrdenDTO> obtenerOrdenPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ordenService.obtenerOrdenPorId(id));
    }

    /**
     * Crear una nueva orden.
     */
    @PostMapping
    public ResponseEntity<Orden> crearOrden(@RequestBody Orden orden) {
        return ResponseEntity.ok(ordenService.crearOrden(orden));
    }

    /**
     * Actualizar una orden por ID.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Orden> actualizarOrden(@PathVariable Long id, @RequestBody Orden orden) {
        return ResponseEntity.ok(ordenService.actualizarOrden(id, orden));
    }

    /**
     * Eliminar una orden por ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOrden(@PathVariable Long id) {
        ordenService.eliminarOrden(id);
        return ResponseEntity.noContent().build();
    }
}

