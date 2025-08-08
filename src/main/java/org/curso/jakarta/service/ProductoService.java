package org.curso.jakarta.service;

import org.curso.jakarta.model.Producto;

import java.util.List;

public interface ProductoService {

    Producto save(Producto p);
    Producto update(Producto p);
    List<Producto> listAll();
    Producto findById(Integer id);
    void delete(Integer id);

}
