package com.cibertec.marketvirtual.Service.Impl;

import com.cibertec.marketvirtual.DTO.OrdenesEstadoHistorialDTO;
import com.cibertec.marketvirtual.Model.OrdenesEstadoHistorial;
import com.cibertec.marketvirtual.Repository.OrdenesEstadoHistorialRepository;
import com.cibertec.marketvirtual.Service.OrdenesEstadoHistorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdenesEstadoHistorialServiceImpl implements OrdenesEstadoHistorialService {

    @Autowired
    private OrdenesEstadoHistorialRepository historialRepository;

    @Override
    public List<OrdenesEstadoHistorialDTO> listarHistorialPorOrdenId(Long ordenId) {
        // Obtener el historial desde el repositorio
        List<OrdenesEstadoHistorial> historial = historialRepository.findByOrdenId(ordenId);

        // Convertir las entidades a DTOs para evitar recursividad
        return historial.stream()
                .map(this::convertirAHistorialDTO)
                .collect(Collectors.toList());
    }

    /**
     * Método para convertir una entidad OrdenesEstadoHistorial a un DTO OrdenEstadoHistorialDTO.
     *
     * @param historial Entidad OrdenesEstadoHistorial.
     * @return DTO OrdenEstadoHistorialDTO.
     */
    private OrdenesEstadoHistorialDTO convertirAHistorialDTO(OrdenesEstadoHistorial historial) {
        OrdenesEstadoHistorialDTO dto = new OrdenesEstadoHistorialDTO();
        dto.setId(historial.getId());
        dto.setOrdenId(historial.getOrden().getId());
        dto.setEstadoAnterior(historial.getEstadoAnterior() != null ? historial.getEstadoAnterior().getNombre() : null);
        dto.setEstadoActual(historial.getEstadoActual().getNombre());
        dto.setCambiadoPor(historial.getCambiadoPor());
        dto.setFechaCambio(historial.getFechaCambio());
        return dto;
    }
}
