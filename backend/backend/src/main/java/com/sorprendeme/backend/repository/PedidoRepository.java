package com.sorprendeme.backend.repository;

import com.sorprendeme.backend.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de Pedido.
 * Por ahora usamos solo los métodos que JpaRepository nos da automáticamente:
 * save(), findById(), findAll(), deleteById(), count(), etc.
 * Aquí puedes agregar consultas personalizadas en el futuro si las necesitas.
 */
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
