package com.sorprendeme.backend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Manejador global de errores.
 *
 * @RestControllerAdvice le dice a Spring que esta clase intercepta
 * todas las excepciones que ocurran en cualquier Controller.
 * En lugar de devolver un error genérico del servidor, devuelve
 * un JSON con un mensaje claro y legible.
 *
 * Así en Postman en lugar de ver:
 *   500 Internal Server Error (sin explicación)
 * Verás:
 *   404 Not Found + { "error": "Producto no encontrado con id: 99" }
 */
@RestControllerAdvice
public class ManejadorDeErrores {

    /**
     * Captura cualquier RuntimeException lanzada en el proyecto.
     * Por ejemplo: "Producto no encontrado con id: 5"
     * Devuelve código 404 con un JSON explicando qué pasó.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> manejarRuntimeException(RuntimeException ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("error", ex.getMessage());
        respuesta.put("timestamp", LocalDateTime.now().toString());
        respuesta.put("estado", 404);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
    }

    /**
     * Captura cualquier error inesperado que no sea RuntimeException.
     * Devuelve código 500 con un mensaje genérico (sin exponer detalles internos).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> manejarExcepcionGeneral(Exception ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("error", "Ocurrió un error interno en el servidor");
        respuesta.put("detalle", ex.getMessage());
        respuesta.put("timestamp", LocalDateTime.now().toString());
        respuesta.put("estado", 500);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
    }
}
