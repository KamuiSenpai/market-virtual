package com.cibertec.marketvirtual.Controller;

import com.cibertec.marketvirtual.Model.Usuario;
import com.cibertec.marketvirtual.Service.UsuarioService;
import com.cibertec.marketvirtual.Utils.JwtUtil;
import com.cibertec.marketvirtual.Utils.LoginAttemptService;

import java.util.HashMap;
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

    @Autowired
    private LoginAttemptService loginAttemptService;

    /**
     * Endpoint para iniciar sesión
     * @param loginRequest mapa con las credenciales de inicio de sesión
     * @return Token JWT y datos del usuario si las credenciales son válidas
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String contrasena = loginRequest.get("contrasena");

        // Verificar si la cuenta está bloqueada
        if (loginAttemptService.isBlocked(email)) {
            return ResponseEntity.status(403).body("La cuenta está bloqueada. Intente nuevamente más tarde.");
        }

        Usuario usuario = usuarioService.login(email, contrasena);

        if (usuario != null) {
            // Restablecer el contador de intentos fallidos
            loginAttemptService.loginSucceeded(email);
            String token = jwtUtil.generateToken(usuario.getEmail());
            return ResponseEntity.ok(Map.of("token", token, "usuario", usuario));
        } else {
            // Incrementar el contador de intentos fallidos
            loginAttemptService.loginFailed(email);
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }

    /**
     * Endpoint para registrar un nuevo usuario
     * @param usuario objeto del usuario a registrar
     * @return Datos del usuario registrado y el token generado
     */
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.registrarUsuario(usuario);
        String token = jwtUtil.generateToken(nuevoUsuario.getEmail());

        // Construir la respuesta
        Map<String, Object> response = new HashMap<>();
        response.put("usuario", nuevoUsuario);
        response.put("token", token);

        return ResponseEntity.ok(response);
    }
}

