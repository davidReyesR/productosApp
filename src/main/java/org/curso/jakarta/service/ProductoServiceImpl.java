package org.curso.jakarta.service;

import jakarta.inject.Inject;
import org.curso.jakarta.model.Categoria;
import org.curso.jakarta.model.Producto;
import org.curso.jakarta.repository.ProductoRepository;
import java.util.List;

public class ProductoServiceImpl implements ProductoService{
    @Inject
    ProductoRepository productoRepository;

    @Override
    public Producto save(Producto p) {
        return productoRepository.save(p);
    }
    @Override
    public Producto update(Producto p) {
        return productoRepository.update(p);
    }
    @Override
    public List<Producto> listAll() {
        return productoRepository.listAll();
    }

    @Override
    public Producto findById(Integer id) {
        return productoRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        productoRepository.delete(id);
    }
}
