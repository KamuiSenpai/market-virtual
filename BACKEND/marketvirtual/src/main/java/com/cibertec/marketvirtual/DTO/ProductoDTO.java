package com.cibertec.marketvirtual.DTO;

import com.cibertec.marketvirtual.Model.Producto;
import java.math.BigDecimal;

public class ProductoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private String categoriaNombre; // Nombre de la categoría
    private String unidadMedidaNombre; // Nombre de la unidad de medida
    private Boolean activo;
    private String imagenUrl;

    // Constructor que convierte un Producto en ProductoDTO
    public ProductoDTO(Producto producto) {
        this.id = producto.getId();
        this.nombre = producto.getNombre();
        this.descripcion = producto.getDescripcion();
        this.precio = producto.getPrecio();
        this.categoriaNombre = producto.getCategoria() != null ? producto.getCategoria().getNombre() : null;
        this.unidadMedidaNombre = producto.getUnidadMedida() != null ? producto.getUnidadMedida().getNombre() : null;
        this.activo = producto.getActivo();
        this.imagenUrl = producto.getImagenUrl();
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }

    public String getUnidadMedidaNombre() {
        return unidadMedidaNombre;
    }

    public void setUnidadMedidaNombre(String unidadMedidaNombre) {
        this.unidadMedidaNombre = unidadMedidaNombre;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
}
