package com.cibertec.marketvirtual.Controller;

import com.cibertec.marketvirtual.Model.Usuario;
import com.cibertec.marketvirtual.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Endpoint para realizar login de usuario.
     *
     * @param email      Email del usuario.
     * @param contrasena Contraseña del usuario.
     * @return Usuario autenticado o mensaje de error.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String contrasena) {
        Usuario usuario = usuarioService.login(email, contrasena);

        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }

    /**
     * Endpoint para registrar un nuevo usuario.
     *
     * @param usuario Objeto Usuario a registrar.
     * @return Usuario registrado.
     */
    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.registrarUsuario(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }
}

