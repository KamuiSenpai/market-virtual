package com.cibertec.marketvirtual.Controller;

import com.cibertec.marketvirtual.Model.OrdenEstado;
import com.cibertec.marketvirtual.Service.OrdenEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estados")
public class OrdenEstadoController {

    @Autowired
    private OrdenEstadoService ordenEstadoService;

    /**
     * Listar todos los estados
     */
    @GetMapping
    public ResponseEntity<List<OrdenEstado>> listarEstados() {
        return ResponseEntity.ok(ordenEstadoService.listarEstados());
    }

    /**
     * Obtener un estado por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrdenEstado> obtenerEstadoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ordenEstadoService.obtenerEstadoPorId(id));
    }

    /**
     * Crear un nuevo estado
     */
    @PostMapping
    public ResponseEntity<OrdenEstado> crearEstado(@RequestBody OrdenEstado estado) {
        return ResponseEntity.ok(ordenEstadoService.crearEstado(estado));
    }

    /**
     * Actualizar un estado existente por ID
     */
    @PutMapping("/{id}")
    public ResponseEntity<OrdenEstado> actualizarEstado(@PathVariable Long id, @RequestBody OrdenEstado estado) {
        return ResponseEntity.ok(ordenEstadoService.actualizarEstado(id, estado));
    }

    /**
     * Eliminar un estado por ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstado(@PathVariable Long id) {
        ordenEstadoService.eliminarEstado(id);
        return ResponseEntity.noContent().build();
    }
}
