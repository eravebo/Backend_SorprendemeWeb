package com.sorprendeme.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

/**
 * Entidad que representa una línea dentro de un pedido.
 * Por ejemplo: "2 unidades de Ancheta Romántica a $85.000 c/u".
 */
@Data
@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Referencia al pedido al que pertenece este detalle.
     *
     * @JsonIgnore evita un bucle infinito al convertir a JSON:
     * sin esto, al serializar un Pedido -> sus detalles -> cada detalle
     * tiene su Pedido -> que tiene sus detalles -> bucle sin fin (StackOverflow).
     */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    // Producto que se está pidiendo en esta línea
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    // Cuántas unidades de ese producto se pidieron
    @Column(nullable = false)
    private Integer cantidad;

    // Precio del producto al momento de hacer el pedido
    // (se guarda separado para no verse afectado si el precio cambia después)
    @Column(nullable = false)
    private Double precio;
}
