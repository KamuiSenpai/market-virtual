package com.cibertec.marketvirtual.Repository;

import com.cibertec.marketvirtual.Model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {
    // Puedes agregar métodos personalizados aquí si los necesitas
}
