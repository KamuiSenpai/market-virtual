package com.cibertec.marketvirtual.Service.Impl;

import com.cibertec.marketvirtual.DTO.DetalleOrdenDTO;
import com.cibertec.marketvirtual.DTO.OrdenDTO;
import com.cibertec.marketvirtual.DTO.UsuarioDTO;
import com.cibertec.marketvirtual.Model.Orden;
import com.cibertec.marketvirtual.Repository.OrdenRepository;
import com.cibertec.marketvirtual.Service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdenServiceImpl implements OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    @Override
    public List<OrdenDTO> listarOrdenes() {
        List<Orden> ordenes = ordenRepository.findAll();
        return ordenes.stream().map(this::convertirAOrdenDTO).collect(Collectors.toList());
    }

    @Override
    public OrdenDTO obtenerOrdenPorId(Long id) {
        Optional<Orden> ordenOptional = ordenRepository.findById(id);
        if (ordenOptional.isPresent()) {
            return convertirAOrdenDTO(ordenOptional.get());
        } else {
            throw new RuntimeException("Orden no encontrada con el ID: " + id);
        }
    }

    @Override
    public Orden crearOrden(Orden orden) {
        return ordenRepository.save(orden);
    }

    @Override
    public Orden actualizarOrden(Long id, Orden orden) {
        Orden ordenExistente = obtenerOrdenEntidadPorId(id);
        ordenExistente.setUsuario(orden.getUsuario());
        ordenExistente.setTotal(orden.getTotal());
        ordenExistente.setEstado(orden.getEstado());
        ordenExistente.setDireccionEntrega(orden.getDireccionEntrega());
        ordenExistente.setObservaciones(orden.getObservaciones());
        ordenExistente.setDetalles(orden.getDetalles());
        return ordenRepository.save(ordenExistente);
    }

    @Override
    public void eliminarOrden(Long id) {
        Orden ordenExistente = obtenerOrdenEntidadPorId(id);
        ordenRepository.delete(ordenExistente);
    }

    /**
     * Método para convertir una entidad Orden en un DTO OrdenDTO
     */
    private OrdenDTO convertirAOrdenDTO(Orden orden) {
        OrdenDTO ordenDTO = new OrdenDTO();
        ordenDTO.setId(orden.getId());
        ordenDTO.setUsuario(new UsuarioDTO(orden.getUsuario().getId(), orden.getUsuario().getNombre(), orden.getUsuario().getEmail()));
        ordenDTO.setTotal(orden.getTotal());
        ordenDTO.setEstado(orden.getEstado().getNombre());
        ordenDTO.setDireccionEntrega(orden.getDireccionEntrega());
        ordenDTO.setObservaciones(orden.getObservaciones());
        ordenDTO.setCreadoEn(orden.getCreadoEn());
        ordenDTO.setDetalles(orden.getDetalles().stream().map(detalle -> {
            DetalleOrdenDTO detalleDTO = new DetalleOrdenDTO();
            detalleDTO.setId(detalle.getId());
            detalleDTO.setProductoNombre(detalle.getProducto().getNombre());
            detalleDTO.setCantidad(detalle.getCantidad());
            detalleDTO.setSubtotal(detalle.getSubtotal());
            return detalleDTO;
        }).collect(Collectors.toList()));
        return ordenDTO;
    }

    /**
     * Método para obtener una entidad Orden por ID
     */
    private Orden obtenerOrdenEntidadPorId(Long id) {
        Optional<Orden> ordenOptional = ordenRepository.findById(id);
        if (ordenOptional.isPresent()) {
            return ordenOptional.get();
        } else {
            throw new RuntimeException("Orden no encontrada con el ID: " + id);
        }
    }
}
