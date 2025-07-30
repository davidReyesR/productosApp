package org.curso.jakarta.service;

import org.curso.jakarta.model.Direccion;

import java.util.List;

public interface DireccionService {
    Direccion save(Direccion d);
    Direccion update(Integer id, Direccion d);
    List<Direccion> listAll();
    Direccion findById(Integer id);
    void delete(Integer id);
}
