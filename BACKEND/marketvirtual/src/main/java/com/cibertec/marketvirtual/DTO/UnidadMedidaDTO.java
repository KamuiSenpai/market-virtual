package com.cibertec.marketvirtual.DTO;

public class UnidadMedidaDTO {
    private Long id;
    private String nombre;

    // Constructor vacío para serialización/deserialización
    public UnidadMedidaDTO() {
    }

    // Constructor con todos los campos
    public UnidadMedidaDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "UnidadMedidaDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
