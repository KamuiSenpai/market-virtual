package com.cibertec.marketvirtual.Repository;

import com.cibertec.marketvirtual.Model.OrdenesEstadoHistorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenesEstadoHistorialRepository extends JpaRepository<OrdenesEstadoHistorial, Long> {

    /**
     * Encuentra el historial de estados por el ID de la orden.
     * 
     * @param ordenId ID de la orden.
     * @return Lista de objetos OrdenesEstadoHistorial.
     */
    List<OrdenesEstadoHistorial> findByOrdenId(Long ordenId);
}

