package com.example.demo.service;

import com.example.demo.model.Producto;
import com.example.demo.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepository repo;

    public ProductoService(ProductoRepository repo) {
        this.repo = repo;
    }

    public List<Producto> listar() {
        return repo.findAll();
    }

    public List<Producto> listarPorCategoria(String categoria) {
        return repo.findByCategoria(categoria);
    }

    public List<Producto> listarPorPrecioMayorQue(Double precio) {
        return repo.findByPrecioMayorQue(precio);
    }

    public Producto guardar(Producto p) {
        return repo.save(p);
    }
}
