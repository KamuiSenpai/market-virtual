package com.cibertec.marketvirtual.Service.Impl;

import com.cibertec.marketvirtual.Model.UnidadMedida;
import com.cibertec.marketvirtual.Repository.UnidadMedidaRepository;
import com.cibertec.marketvirtual.Service.UnidadMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadMedidaServiceImpl implements UnidadMedidaService {

    @Autowired
    private UnidadMedidaRepository unidadMedidaRepository;

    @Override
    public List<UnidadMedida> listarUnidadesMedida() {
        return unidadMedidaRepository.findAll();
    }

    @Override
    public UnidadMedida obtenerUnidadMedidaPorId(Long id) {
        return unidadMedidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unidad de Medida no encontrada"));
    }

    @Override
    public UnidadMedida crearUnidadMedida(UnidadMedida unidadMedida) {
        return unidadMedidaRepository.save(unidadMedida);
    }

    @Override
    public UnidadMedida actualizarUnidadMedida(Long id, UnidadMedida unidadMedida) {
        UnidadMedida unidadExistente = obtenerUnidadMedidaPorId(id);
        unidadExistente.setNombre(unidadMedida.getNombre());
        return unidadMedidaRepository.save(unidadExistente);
    }

    @Override
    public void eliminarUnidadMedida(Long id) {
        unidadMedidaRepository.deleteById(id);
    }
}
