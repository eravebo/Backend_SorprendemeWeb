package com.sorprendeme.backend.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entidad que representa un producto del catálogo.
 * @Data de Lombok genera automáticamente getters, setters, toString y equals.
 */
@Data
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre del producto, obligatorio, máximo 100 caracteres
    @Column(nullable = false, length = 100)
    private String nombre;

    // Descripción larga del producto
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    // Precio con hasta 10 dígitos y 2 decimales
    @Column(nullable = false)
    private Double precio;

    // Categoría del producto (ej: "ancheta", "peluche")
    @Column(nullable = false, length = 20)
    private String categoria;

    // URL de la imagen del producto
    @Column(nullable = false, length = 255)
    private String imagenUrl;

    // Cantidad disponible en inventario
    @Column(nullable = false)
    private Integer stock;

    // Por defecto el producto está activo al crearlo
    private boolean activo = true;
}
