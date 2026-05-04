package com.sorprendeme.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Spring Boot.
 *
 * @SpringBootApplication es una combinación de tres anotaciones:
 *   - @Configuration: esta clase puede definir beans de Spring
 *   - @EnableAutoConfiguration: Spring configura automáticamente lo que detecta (JPA, Web, H2, etc.)
 *   - @ComponentScan: Spring busca componentes (@Service, @Repository, @Controller, etc.)
 *     en este paquete y sus subpaquetes automáticamente
 *
 * Por eso es importante que todas las clases estén dentro del paquete
 * com.sorprendeme.backend (o subpaquetes de él).
 */
@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
}
