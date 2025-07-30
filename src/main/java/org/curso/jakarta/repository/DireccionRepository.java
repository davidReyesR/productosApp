package org.curso.jakarta.repository;

import org.curso.jakarta.model.Direccion;

import java.util.List;

public interface DireccionRepository {
    Direccion save(Direccion d);
    List<Direccion> listAll();
    Direccion findById(Integer id);
    void delete(Integer id);
}
