package org.curso.jakarta.service;

import jakarta.inject.Inject;
import org.curso.jakarta.model.Carro;
import org.curso.jakarta.repository.CarroRepository;

import java.util.List;

public class CarroServiceImpl implements CarroService{
    @Inject
    CarroRepository carroRepository;

    @Override
    public Carro save(Carro c) {
        return carroRepository.save(c);
    }

    @Override
    public Carro update(Integer id, Carro c) {
        c.setId(id);
        return carroRepository.save(c);
    }

    @Override
    public List<Carro> listAll() {
        return carroRepository.listAll();
    }

    @Override
    public Carro findById(Integer id) {
        return carroRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        carroRepository.delete(id);
    }
}
