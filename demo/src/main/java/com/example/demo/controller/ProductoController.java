package com.example.demo.controller;

import com.example.demo.model.Producto;
import com.example.demo.repository.ProductoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductoRepository repo;

    public ProductoController(ProductoRepository repo) {
        this.repo = repo;
    }

    // Listar todos los productos
    @GetMapping
    public List<Producto> listar() {
        return repo.findAll();
    }

    // Crear producto
    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody Producto p) {
        Producto nuevo = repo.save(p);
        return ResponseEntity.ok(nuevo);
    }

    // Actualizar producto
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto p) {
        Optional<Producto> opt = repo.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Producto existente = opt.get();
        existente.setNombre(p.getNombre());
        existente.setCategoria(p.getCategoria());
        existente.setPrecio(p.getPrecio());
        Producto actualizado = repo.save(existente);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
