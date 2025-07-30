package org.curso.jakarta.service;

import org.curso.jakarta.model.Categoria;
import org.curso.jakarta.model.Producto;

import java.util.List;

public interface ProductoService {

    Producto save(Producto p);
    Producto update(Integer id, Producto p);
    List<Producto> listAll();
    Producto findById(Integer id);
    void delete(Integer id);

}
