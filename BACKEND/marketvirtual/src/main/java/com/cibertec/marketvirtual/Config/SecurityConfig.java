package com.cibertec.marketvirtual.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Deshabilitar CSRF solo para pruebas locales (recomendado habilitar en producción)
            .csrf(csrf -> csrf.disable())
            // Configurar autorización de solicitudes
            .authorizeHttpRequests(auth -> auth
                // Permitir acceso público a los endpoints de login y registro
                .requestMatchers("/api/usuarios/registrar", "/api/usuarios/login").permitAll()
                // Requiere autenticación para cualquier otra solicitud
                .anyRequest().authenticated()
            )
            // Deshabilitar la funcionalidad de inicio de sesión basado en formularios
            .formLogin(form -> form.disable());

        return http.build();
    }
}
