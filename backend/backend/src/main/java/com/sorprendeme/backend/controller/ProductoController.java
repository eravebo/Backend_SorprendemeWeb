package com.sorprendeme.backend.controller;

import com.sorprendeme.backend.model.Producto;
import com.sorprendeme.backend.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para el recurso Producto.
 * Define los endpoints que el frontend puede llamar.
 *
 * @CrossOrigin(origins = "*") permite que el frontend (aunque esté en otro puerto
 * o dominio) pueda hacer peticiones a este backend. En producción deberías
 * reemplazar "*" por la URL exacta de tu frontend.
 */
@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    /**
     * GET /api/productos           → retorna todos los productos activos
     * GET /api/productos?categoria=ancheta → filtra por categoría
     *
     * @RequestParam(required = false) significa que el parámetro es opcional
     */
    @GetMapping
    public List<Producto> obtenerProductos(
            @RequestParam(required = false) String categoria) {

        if (categoria != null && !categoria.isEmpty()) {
            return productoService.obtenerPorCategoria(categoria);
        }
        return productoService.obtenerTodos();
    }

    /**
     * POST /api/productos
     * Crea un producto nuevo. El cuerpo del request debe ser un JSON con los datos.
     * ResponseEntity.ok() devuelve el producto guardado con código HTTP 200.
     */
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        Producto guardado = productoService.guardar(producto);
        return ResponseEntity.ok(guardado);
    }

    /**
     * DELETE /api/productos/{id}
     * No borra el producto físicamente, solo lo marca como inactivo.
     * ResponseEntity.noContent() devuelve código HTTP 204 (sin contenido).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivarProducto(@PathVariable Long id) {
        productoService.desactivar(id);
        return ResponseEntity.noContent().build();
    }
}
