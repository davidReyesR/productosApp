package org.curso.jakarta.service;

import jakarta.inject.Inject;
import org.curso.jakarta.model.Cliente;
import org.curso.jakarta.repository.ClienteRepository;

import java.util.List;

public class ClienteServiceImpl implements ClienteService {
    @Inject
    ClienteRepository clienteRepository;

    @Override
    public Cliente save(Cliente c) {
        return clienteRepository.save(c);
    }

    @Override
    public Cliente update(Integer id, Cliente c) {
        c.setId(id);
        return clienteRepository.save(c);
    }

    @Override
    public List<Cliente> listAll() {
        return clienteRepository.listAll();
    }

    @Override
    public Cliente findById(Integer id) {
        return clienteRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        clienteRepository.delete(id);
    }
}
