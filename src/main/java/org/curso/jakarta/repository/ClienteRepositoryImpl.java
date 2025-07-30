package org.curso.jakarta.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.curso.jakarta.model.Carro;
import org.curso.jakarta.model.Cliente;

import java.util.List;

public class ClienteRepositoryImpl implements ClienteRepository{
    @PersistenceContext(unitName = "productoJPA")
    EntityManager entityManager;

    @Override
    @Transactional
    public Cliente save(Cliente c) {
        if(c.getId() == null) {
            entityManager.persist(c);
            return c;
        }else{
            if(findById(c.getId())!= null) {
                entityManager.merge(c);
                return c;
            }else{
                return null;
            }
        }
    }

    @Override
    public List<Cliente> listAll() {
        return entityManager.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
    }

    @Override
    public Cliente findById(Integer id) {
        return entityManager.find(Cliente.class, id);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        entityManager.remove(entityManager.getReference(Cliente.class, id));
    }
}
