package org.curso.jakarta.repository;

import org.curso.jakarta.model.Categoria;
import java.util.List;

public interface CategoriaRepository {
    Categoria save(Categoria c);
    List<Categoria> listAll();
    Categoria findById(Integer id);
    void delete(Integer id);
}
