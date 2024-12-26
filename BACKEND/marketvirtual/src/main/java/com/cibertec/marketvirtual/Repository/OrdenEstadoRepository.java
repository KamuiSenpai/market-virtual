package com.cibertec.marketvirtual.Repository;

import com.cibertec.marketvirtual.Model.OrdenEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenEstadoRepository extends JpaRepository<OrdenEstado, Long> {
}
