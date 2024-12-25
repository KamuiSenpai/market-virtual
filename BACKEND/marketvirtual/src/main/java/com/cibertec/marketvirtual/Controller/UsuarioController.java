package com.cibertec.marketvirtual.Controller;

import com.cibertec.marketvirtual.Model.Usuario;
import com.cibertec.marketvirtual.Service.UsuarioService;
import com.cibertec.marketvirtual.Utils.JwtUtil;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String contrasena) {
        Usuario usuario = usuarioService.login(email, contrasena);
        if (usuario != null) {
            String token = jwtUtil.generateToken(usuario.getEmail());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.registrarUsuario(usuario);
        String token = jwtUtil.generateToken(nuevoUsuario.getEmail()); // Generar token
        return ResponseEntity.ok(Map.of("usuario", nuevoUsuario, "token", token)); // Devuelve usuario y token
    }


}
