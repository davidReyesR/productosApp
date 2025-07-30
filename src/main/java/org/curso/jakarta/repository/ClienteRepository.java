package org.curso.jakarta.repository;

import org.curso.jakarta.model.Cliente;

import java.util.List;

public interface ClienteRepository {
    Cliente save(Cliente c);
    List<Cliente> listAll();
    Cliente findById(Integer id);
    void delete(Integer id);
}
