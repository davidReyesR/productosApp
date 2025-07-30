package org.curso.jakarta.repository;

import org.curso.jakarta.model.Producto;

import java.util.List;

public interface ProductoRepository {
    Producto save(Producto p);
    List<Producto> listAll();
    Producto findById(Integer id);
    void delete(Integer id);
}
