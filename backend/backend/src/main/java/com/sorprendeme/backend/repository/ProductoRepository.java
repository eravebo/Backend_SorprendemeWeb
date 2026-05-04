package com.sorprendeme.backend.repository;

import com.sorprendeme.backend.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repositorio de Producto.
 * JpaRepository nos da gratis: save(), findById(), findAll(), delete(), count(), etc.
 * Los métodos personalizados abajo son generados automáticamente por Spring
 * interpretando el nombre del método (no necesitas escribir SQL).
 */
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Busca productos de una categoría específica que estén activos
    // Spring traduce esto a: WHERE categoria = ? AND activo = true
    List<Producto> findByCategoriaAndActivoTrue(String categoria);

    // Busca todos los productos que estén activos
    // Spring traduce esto a: WHERE activo = true
    List<Producto> findByActivoTrue();
}
