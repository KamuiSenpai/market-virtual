package com.cibertec.marketvirtual.Utils;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
public class LoginAttemptService {

    private static final int MAX_ATTEMPTS = 3; // Número máximo de intentos permitidos
    private static final long BLOCK_TIME = TimeUnit.MINUTES.toMillis(15); // Tiempo de bloqueo en milisegundos

    private final Map<String, Integer> attemptsCache = new ConcurrentHashMap<>();
    private final Map<String, Long> blockCache = new ConcurrentHashMap<>();

    /**
     * Registra un inicio de sesión fallido para el usuario.
     *
     * @param email el correo electrónico del usuario
     */
    public void loginFailed(String email) {
        if (isBlocked(email)) {
            return; // Si está bloqueado, no hace nada
        }

        int attempts = attemptsCache.getOrDefault(email, 0);
        attempts++;
        attemptsCache.put(email, attempts);

        if (attempts >= MAX_ATTEMPTS) {
            blockCache.put(email, System.currentTimeMillis());
        }
    }

    /**
     * Restablece el contador de intentos fallidos cuando un usuario inicia sesión con éxito.
     *
     * @param email el correo electrónico del usuario
     */
    public void loginSucceeded(String email) {
        attemptsCache.remove(email); // Restablecer intentos
        blockCache.remove(email); // Eliminar bloqueo si existe
    }

    /**
     * Verifica si un usuario está bloqueado.
     *
     * @param email el correo electrónico del usuario
     * @return true si el usuario está bloqueado, false de lo contrario
     */
    public boolean isBlocked(String email) {
        if (!blockCache.containsKey(email)) {
            return false; // No está bloqueado
        }

        long blockStartTime = blockCache.get(email);
        if (System.currentTimeMillis() - blockStartTime > BLOCK_TIME) {
            // Si el tiempo de bloqueo ha expirado, eliminar de la lista de bloqueos
            blockCache.remove(email);
            return false;
        }

        return true; // Sigue bloqueado
    }
}
