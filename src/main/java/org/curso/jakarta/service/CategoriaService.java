package org.curso.jakarta.service;

import org.curso.jakarta.model.Categoria;

import java.util.List;

public interface CategoriaService {
    Categoria save(Categoria p);
    Categoria update(Integer id, Categoria c);
    List<Categoria> listAll();
    Categoria findById(Integer id);
    void delete(Integer id);
}
