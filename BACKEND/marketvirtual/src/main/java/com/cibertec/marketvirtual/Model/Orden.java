package com.cibertec.marketvirtual.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ordenes")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private Double total;

    @ManyToOne
    @JoinColumn(name = "estado_id", nullable = false)
    private OrdenEstado estado;

    @Column(name = "direccion_entrega", nullable = false, length = 255)
    private String direccionEntrega;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    @Column(name = "creado_en", updatable = false)
    private LocalDateTime creadoEn;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleOrden> detalles;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdenesEstadoHistorial> historialEstados;

    public Orden() {
        this.creadoEn = LocalDateTime.now();
    }

    public Orden(Usuario usuario, Double total, OrdenEstado estado, String direccionEntrega, String observaciones) {
        this.usuario = usuario;
        this.total = total;
        this.estado = estado;
        this.direccionEntrega = direccionEntrega;
        this.observaciones = observaciones;
        this.creadoEn = LocalDateTime.now();
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public OrdenEstado getEstado() {
        return estado;
    }

    public void setEstado(OrdenEstado estado) {
        this.estado = estado;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }

    public List<DetalleOrden> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleOrden> detalles) {
        this.detalles = detalles;
    }

    public List<OrdenesEstadoHistorial> getHistorialEstados() {
        return historialEstados;
    }

    public void setHistorialEstados(List<OrdenesEstadoHistorial> historialEstados) {
        this.historialEstados = historialEstados;
    }
}
