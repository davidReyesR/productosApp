package org.curso.jakarta.service;

import jakarta.inject.Inject;
import org.curso.jakarta.model.Direccion;
import org.curso.jakarta.repository.DireccionRepository;

import java.util.List;

public class DireccionServiceImpl implements DireccionService{
    @Inject
    DireccionRepository direccionRepository;

    @Override
    public Direccion save(Direccion d) {
        return direccionRepository.save(d);
    }

    @Override
    public Direccion update(Integer id, Direccion d) {
        d.setId(id);
        return direccionRepository.save(d);
    }

    @Override
    public List<Direccion> listAll() {
        return direccionRepository.listAll();
    }

    @Override
    public Direccion findById(Integer id) {
        return direccionRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        direccionRepository.delete(id);
    }
}
