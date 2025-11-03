package com.example.demo.repository;

import com.example.demo.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Consulta derivada
    List<Producto> findByCategoria(String categoria);

    // Consulta JPQL personalizada
    @Query("SELECT p FROM Producto p WHERE p.precio > :precio")
    List<Producto> findByPrecioMayorQue(@Param("precio") Double precio);
}
