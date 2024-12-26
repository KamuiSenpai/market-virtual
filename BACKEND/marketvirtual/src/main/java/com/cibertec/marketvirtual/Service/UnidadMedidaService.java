package com.cibertec.marketvirtual.Service;

import com.cibertec.marketvirtual.Model.UnidadMedida;

import java.util.List;

public interface UnidadMedidaService {
    List<UnidadMedida> listarUnidadesMedida();
    UnidadMedida obtenerUnidadMedidaPorId(Long id);
    UnidadMedida crearUnidadMedida(UnidadMedida unidadMedida);
    UnidadMedida actualizarUnidadMedida(Long id, UnidadMedida unidadMedida);
    void eliminarUnidadMedida(Long id);
}
