package org.curso.jakarta.service;

import org.curso.jakarta.model.Carro;
import java.util.List;

public interface CarroService {
    Carro save(Carro c);
    Carro update(Integer id, Carro c);
    List<Carro> listAll();
    Carro findById(Integer id);
    void delete(Integer id);
}
