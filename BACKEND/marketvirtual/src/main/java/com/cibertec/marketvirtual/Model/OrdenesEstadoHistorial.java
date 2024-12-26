package com.cibertec.marketvirtual.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ordenes_estados_historial")
public class OrdenesEstadoHistorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orden_id", nullable = false)
    private Orden orden;

    @ManyToOne
    @JoinColumn(name = "estado_anterior_id")
    private OrdenEstado estadoAnterior;

    @ManyToOne
    @JoinColumn(name = "estado_actual_id", nullable = false)
    private OrdenEstado estadoActual;

    @Column(name = "cambiado_por", length = 100)
    private String cambiadoPor;

    @Column(name = "fecha_cambio", nullable = false)
    private LocalDateTime fechaCambio;

    public OrdenesEstadoHistorial() {
        this.fechaCambio = LocalDateTime.now();
    }

    public OrdenesEstadoHistorial(Orden orden, OrdenEstado estadoAnterior, OrdenEstado estadoActual, String cambiadoPor) {
        this.orden = orden;
        this.estadoAnterior = estadoAnterior;
        this.estadoActual = estadoActual;
        this.cambiadoPor = cambiadoPor;
        this.fechaCambio = LocalDateTime.now();
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public OrdenEstado getEstadoAnterior() {
        return estadoAnterior;
    }

    public void setEstadoAnterior(OrdenEstado estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }

    public OrdenEstado getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(OrdenEstado estadoActual) {
        this.estadoActual = estadoActual;
    }

    public String getCambiadoPor() {
        return cambiadoPor;
    }

    public void setCambiadoPor(String cambiadoPor) {
        this.cambiadoPor = cambiadoPor;
    }

    public LocalDateTime getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(LocalDateTime fechaCambio) {
        this.fechaCambio = fechaCambio;
    }
}
