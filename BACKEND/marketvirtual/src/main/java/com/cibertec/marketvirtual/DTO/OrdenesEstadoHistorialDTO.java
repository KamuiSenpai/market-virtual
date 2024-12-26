package com.cibertec.marketvirtual.DTO;

import java.time.LocalDateTime;

public class OrdenesEstadoHistorialDTO {
    private Long id;
    private Long ordenId;
    private String estadoAnterior;
    private String estadoActual;
    private String cambiadoPor;
    private LocalDateTime fechaCambio;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrdenId() {
        return ordenId;
    }

    public void setOrdenId(Long ordenId) {
        this.ordenId = ordenId;
    }

    public String getEstadoAnterior() {
        return estadoAnterior;
    }

    public void setEstadoAnterior(String estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(String estadoActual) {
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
