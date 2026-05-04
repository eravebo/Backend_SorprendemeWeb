package com.sorprendeme.backend.service;

import com.sorprendeme.backend.model.Producto;
import com.sorprendeme.backend.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio de Producto.
 * Aquí va la lógica de negocio relacionada con productos.
 * El controlador no debe hablar directamente con el repositorio,
 * siempre pasa por el servicio. Eso mantiene el código organizado.
 */
@Service
public class ProductoService {

    // Inyección por constructor (mejor práctica que @Autowired en el campo)
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Retorna todos los productos que están activos
    public List<Producto> obtenerTodos() {
        try {
            return productoRepository.findByActivoTrue();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los productos: " + e.getMessage());
        }
    }

    // Retorna los productos activos de una categoría específica
    public List<Producto> obtenerPorCategoria(String categoria) {
        try {
            return productoRepository.findByCategoriaAndActivoTrue(categoria);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener productos de la categoría '" + categoria + "': " + e.getMessage());
        }
    }

    // Guarda un producto nuevo o actualiza uno existente
    public Producto guardar(Producto producto) {
        try {
            return productoRepository.save(producto);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el producto: " + e.getMessage());
        }
    }

    /**
     * "Elimina" un producto marcándolo como inactivo (activo = false).
     * Esto se llama "borrado lógico": el producto sigue en la base de datos
     * pero no aparece en las consultas normales. Es mejor práctica que
     * borrarlo físicamente, porque conserva el historial de pedidos.
     */
    public void desactivar(Long id) {
        try {
            Producto producto = productoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
            producto.setActivo(false);
            productoRepository.save(producto);
        } catch (RuntimeException e) {
            // Relanzamos para que el ManejadorDeErrores la convierta en respuesta JSON
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al desactivar el producto con id " + id + ": " + e.getMessage());
        }
    }
}
