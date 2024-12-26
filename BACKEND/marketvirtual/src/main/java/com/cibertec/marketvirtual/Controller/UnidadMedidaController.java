package com.cibertec.marketvirtual.Controller;

import com.cibertec.marketvirtual.DTO.UnidadMedidaDTO;
import com.cibertec.marketvirtual.Model.UnidadMedida;
import com.cibertec.marketvirtual.Service.UnidadMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/unidades-medida")
public class UnidadMedidaController {

    @Autowired
    private UnidadMedidaService unidadMedidaService;

    /**
     * Listar todas las unidades de medida
     */
    @GetMapping
    public ResponseEntity<List<UnidadMedidaDTO>> listarUnidadesMedida() {
        List<UnidadMedidaDTO> unidades = unidadMedidaService.listarUnidadesMedida().stream()
                .map(unidad -> new UnidadMedidaDTO(unidad.getId(), unidad.getNombre()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(unidades);
    }

    /**
     * Obtener una unidad de medida por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<UnidadMedidaDTO> obtenerUnidadMedidaPorId(@PathVariable Long id) {
        UnidadMedida unidad = unidadMedidaService.obtenerUnidadMedidaPorId(id);
        UnidadMedidaDTO unidadDTO = new UnidadMedidaDTO(unidad.getId(), unidad.getNombre());
        return ResponseEntity.ok(unidadDTO);
    }

    /**
     * Crear una nueva unidad de medida
     */
    @PostMapping
    public ResponseEntity<UnidadMedidaDTO> crearUnidadMedida(@RequestBody UnidadMedidaDTO unidadMedidaDTO) {
        UnidadMedida nuevaUnidad = new UnidadMedida();
        nuevaUnidad.setNombre(unidadMedidaDTO.getNombre());
        UnidadMedida unidadCreada = unidadMedidaService.crearUnidadMedida(nuevaUnidad);

        UnidadMedidaDTO unidadCreadaDTO = new UnidadMedidaDTO(unidadCreada.getId(), unidadCreada.getNombre());
        return ResponseEntity.ok(unidadCreadaDTO);
    }

    /**
     * Actualizar una unidad de medida por ID
     */
    @PutMapping("/{id}")
    public ResponseEntity<UnidadMedidaDTO> actualizarUnidadMedida(@PathVariable Long id, @RequestBody UnidadMedidaDTO unidadMedidaDTO) {
        UnidadMedida unidadExistente = unidadMedidaService.obtenerUnidadMedidaPorId(id);
        unidadExistente.setNombre(unidadMedidaDTO.getNombre());
        UnidadMedida unidadActualizada = unidadMedidaService.actualizarUnidadMedida(id, unidadExistente);

        UnidadMedidaDTO unidadActualizadaDTO = new UnidadMedidaDTO(unidadActualizada.getId(), unidadActualizada.getNombre());
        return ResponseEntity.ok(unidadActualizadaDTO);
    }

    /**
     * Eliminar una unidad de medida por ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUnidadMedida(@PathVariable Long id) {
        unidadMedidaService.eliminarUnidadMedida(id);
        return ResponseEntity.noContent().build();
    }
}

