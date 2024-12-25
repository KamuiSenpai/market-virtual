package com.cibertec.marketvirtual.Service;

import com.cibertec.marketvirtual.Model.Usuario;

public interface UsuarioService {
    Usuario login(String email, String contrasena);
    Usuario registrarUsuario(Usuario usuario);

}
