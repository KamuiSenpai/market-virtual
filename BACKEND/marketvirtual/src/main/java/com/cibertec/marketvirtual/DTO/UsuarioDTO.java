package com.cibertec.marketvirtual.DTO;

public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String email;

    // Getters y Setters

    public UsuarioDTO(Integer id2, String nombre2, String email2) {
		// TODO Auto-generated constructor stub
	}

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
