package com.cibertec.marketvirtual.Service.Impl;

import com.cibertec.marketvirtual.Model.OrdenEstado;
import com.cibertec.marketvirtual.Repository.OrdenEstadoRepository;
import com.cibertec.marketvirtual.Service.OrdenEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenEstadoServiceImpl implements OrdenEstadoService {

    @Autowired
    private OrdenEstadoRepository ordenEstadoRepository;

    @Override
    public List<OrdenEstado> listarEstados() {
        return ordenEstadoRepository.findAll();
    }

    @Override
    public OrdenEstado obtenerEstadoPorId(Long id) {
        return ordenEstadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado con ID: " + id));
    }

    @Override
    public OrdenEstado crearEstado(OrdenEstado estado) {
        return ordenEstadoRepository.save(estado);
    }

    @Override
    public OrdenEstado actualizarEstado(Long id, OrdenEstado estado) {
        OrdenEstado estadoExistente = obtenerEstadoPorId(id);
        estadoExistente.setNombre(estado.getNombre());
        estadoExistente.setDescripcion(estado.getDescripcion());
        return ordenEstadoRepository.save(estadoExistente);
    }

    @Override
    public void eliminarEstado(Long id) {
        OrdenEstado estado = obtenerEstadoPorId(id);
        ordenEstadoRepository.delete(estado);
    }
}
