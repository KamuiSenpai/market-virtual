package com.cibertec.marketvirtual.Service.Impl;

import com.cibertec.marketvirtual.Model.Usuario;
import com.cibertec.marketvirtual.Repository.UsuarioRepository;
import com.cibertec.marketvirtual.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Usuario registrarUsuario(Usuario usuario) {
        // Encriptar la contraseña antes de guardar
        String contrasenaEncriptada = passwordEncoder.encode(usuario.getContrasena());
        usuario.setContrasena(contrasenaEncriptada);

        // Guardar usuario en la base de datos
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario login(String email, String contrasena) {
        // Buscar usuario por email
        Usuario usuario = usuarioRepository.findByEmail(email);

        // Validar si el usuario existe y la contraseña coincide
        if (usuario != null && passwordEncoder.matches(contrasena, usuario.getContrasena())) {
            return usuario; // Usuario autenticado
        }

        return null; // Credenciales inválidas
    }
}

