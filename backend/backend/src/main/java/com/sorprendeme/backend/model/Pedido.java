package com.sorprendeme.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidad que representa un pedido realizado por un cliente.
 * Un pedido contiene uno o varios DetallePedido (relación uno a muchos).
 */
@Data
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Datos del cliente que realiza el pedido
    @Column(nullable = false, length = 100)
    private String nombreCliente;

    @Column(nullable = false, length = 20)
    private String telefono;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String direccionEnvio;

    // Fecha y hora en que se creó el pedido (se asigna automáticamente)
    @Column(nullable = false)
    private LocalDateTime fecha = LocalDateTime.now();

    // Estado del pedido: "pendiente", "pagado", "enviado", "entregado"
    @Column(nullable = false, length = 20)
    private String estado = "pendiente";

    // Campos opcionales para integración con MercadoPago
    private String mpPaymentId;
    private String mpStatus;

    /**
     * Lista de productos incluidos en este pedido.
     * CascadeType.ALL significa que si se guarda/elimina el pedido,
     * también se guardan/eliminan sus detalles automáticamente.
     */
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<DetallePedido> detalles;
}
