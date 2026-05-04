package com.sorprendeme.backend.controller;

import com.sorprendeme.backend.model.Pedido;
import com.sorprendeme.backend.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para el recurso Pedido.
 * Expone los endpoints para crear pedidos y gestionar su estado.
 */
@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    /**
     * POST /api/pedidos
     * Recibe los datos del pedido en formato JSON y lo guarda.
     */
    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {
        Pedido nuevo = pedidoService.crearPedido(pedido);
        return ResponseEntity.ok(nuevo);
    }

    /**
     * GET /api/pedidos
     * Retorna todos los pedidos. Útil para el panel de administración.
     */
    @GetMapping
    public List<Pedido> obtenerTodos() {
        return pedidoService.obtenerTodos();
    }

    /**
     * PATCH /api/pedidos/{id}/estado?estado=pagado
     * Actualiza solo el estado de un pedido sin tocar los demás datos.
     * Se usa PATCH (y no PUT) porque solo estamos modificando un campo parcialmente.
     */
    @PatchMapping("/{id}/estado")
    public ResponseEntity<Pedido> cambiarEstado(
            @PathVariable Long id,
            @RequestParam String estado) {
        return ResponseEntity.ok(pedidoService.cambiarEstado(id, estado));
    }
}
