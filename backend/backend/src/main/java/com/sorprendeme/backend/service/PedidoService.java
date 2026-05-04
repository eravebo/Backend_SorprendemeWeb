package com.sorprendeme.backend.service;

import com.sorprendeme.backend.model.Pedido;
import com.sorprendeme.backend.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio de Pedido.
 * Contiene la lógica de negocio para crear y gestionar pedidos.
 */
@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    // Guarda un pedido nuevo en la base de datos
    public Pedido crearPedido(Pedido pedido) {
        try {
            return pedidoRepository.save(pedido);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el pedido: " + e.getMessage());
        }
    }

    // Retorna la lista completa de pedidos (útil para el panel de administración)
    public List<Pedido> obtenerTodos() {
        try {
            return pedidoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los pedidos: " + e.getMessage());
        }
    }

    /**
     * Cambia el estado de un pedido.
     * Los estados posibles son: "pendiente", "pagado", "enviado", "entregado".
     * orElseThrow lanza una excepción si el pedido no existe,
     * en lugar de retornar null y causar un NullPointerException después.
     */
    public Pedido cambiarEstado(Long id, String nuevoEstado) {
        try {
            Pedido pedido = pedidoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));
            pedido.setEstado(nuevoEstado);
            return pedidoRepository.save(pedido);
        } catch (RuntimeException e) {
            // Relanzamos para que el ManejadorDeErrores la convierta en respuesta JSON
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al cambiar el estado del pedido con id " + id + ": " + e.getMessage());
        }
    }
}
