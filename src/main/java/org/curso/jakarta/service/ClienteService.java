package org.curso.jakarta.service;

import org.curso.jakarta.model.Cliente;

import java.util.List;

public interface ClienteService {
    Cliente save(Cliente c);
    Cliente update(Integer id, Cliente c);
    List<Cliente> listAll();
    Cliente findById(Integer id);
    void delete(Integer id);
}
