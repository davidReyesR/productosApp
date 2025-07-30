package org.curso.jakarta.service;

import jakarta.inject.Inject;
import org.curso.jakarta.model.Categoria;
import org.curso.jakarta.repository.CategoriaRepository;

import java.util.List;

public class CategoriaServiceImpl implements CategoriaService{
    @Inject
    CategoriaRepository categoriaRepository;

    @Override
    public Categoria save(Categoria c) {
        return categoriaRepository.save(c);
    }

    @Override
    public Categoria update(Integer id, Categoria c) {
        c.setId(id);
        return categoriaRepository.save(c);
    }

    @Override
    public List<Categoria> listAll() {
        return categoriaRepository.listAll();
    }

    @Override
    public Categoria findById(Integer id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        categoriaRepository.delete(id);
    }

}
