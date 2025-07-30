package org.curso.jakarta.repository;

import org.curso.jakarta.model.Carro;
import java.util.List;

public interface CarroRepository {
    Carro save(Carro c);
    List<Carro> listAll();
    Carro findById(Integer id);
    void delete(Integer id);
}
