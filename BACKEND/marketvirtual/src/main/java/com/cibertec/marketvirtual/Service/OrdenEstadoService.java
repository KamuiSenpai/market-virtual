package com.cibertec.marketvirtual.Service;

import com.cibertec.marketvirtual.Model.OrdenEstado;
import java.util.List;

public interface OrdenEstadoService {
    List<OrdenEstado> listarEstados();
    OrdenEstado obtenerEstadoPorId(Long id);
    OrdenEstado crearEstado(OrdenEstado estado);
    OrdenEstado actualizarEstado(Long id, OrdenEstado estado);
    void eliminarEstado(Long id);
}
