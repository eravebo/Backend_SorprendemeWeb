package com.sorprendeme.backend;

import com.sorprendeme.backend.model.Producto;
import com.sorprendeme.backend.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * DataLoader: se ejecuta automáticamente al iniciar la aplicación.
 * Su función es cargar datos de prueba en la base de datos H2 (en memoria).
 *
 * CommandLineRunner es una interfaz de Spring Boot que nos permite ejecutar
 * código justo después de que la aplicación arranca.
 *
 * IMPORTANTE: Como la base de datos es "en memoria" (H2 con create-drop),
 * estos datos se pierden cada vez que se detiene la aplicación.
 * En producción usarías una base de datos real (MySQL, PostgreSQL).
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final ProductoRepository productoRepository;

    public DataLoader(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public void run(String... args) {
        // Solo carga los datos si la tabla está vacía (evita duplicados al reiniciar)
        if (productoRepository.count() == 0) {
            cargarProductos();
        }
    }

    private void cargarProductos() {

        // --- Categoría: Anchetas ---

        Producto p1 = new Producto();
        p1.setNombre("Ancheta Romántica");
        p1.setDescripcion("Chocolates artesanales, vino rosado y detalles especiales");
        p1.setPrecio(85000.0);
        p1.setCategoria("ancheta");
        p1.setImagenUrl("../img/Ancheta_1.jpeg");
        p1.setStock(10);

        Producto p2 = new Producto();
        p2.setNombre("Ancheta Termo GYM");
        p2.setDescripcion("Ancheta con termo para GYM y peluche");
        p2.setPrecio(65000.0);
        p2.setCategoria("ancheta");
        p2.setImagenUrl("../img/Ancheta_2.jpeg");
        p2.setStock(10);

        Producto p3 = new Producto();
        p3.setNombre("Ancheta con Peluche y Frutas");
        p3.setDescripcion("Ancheta con peluche de oso, flores y frutas");
        p3.setPrecio(55000.0);
        p3.setCategoria("ancheta");
        p3.setImagenUrl("../img/Ancheta_3.jpeg");
        p3.setStock(10);

        Producto p4 = new Producto();
        p4.setNombre("Ancheta Peluche Lucifer");
        p4.setDescripcion("Ancheta con peluche temático de Lucifer con chocolates");
        p4.setPrecio(75000.0);
        p4.setCategoria("ancheta");
        p4.setImagenUrl("../img/Ancheta_4.jpeg");
        p4.setStock(10);

        // --- Categoría: Peluches ---

        Producto p5 = new Producto();
        p5.setNombre("Peluche Lotso Dormilón");
        p5.setDescripcion("Peluche temático de Lotso Dormilón con flores eternas");
        p5.setPrecio(115000.0);
        p5.setCategoria("peluche");
        p5.setImagenUrl("../img/Peluche_1.jpeg");
        p5.setStock(5);

        // Guardar todos en la base de datos
        productoRepository.save(p1);
        productoRepository.save(p2);
        productoRepository.save(p3);
        productoRepository.save(p4);
        productoRepository.save(p5);

        System.out.println(">>> ¡Productos de prueba cargados correctamente!");
    }
}
