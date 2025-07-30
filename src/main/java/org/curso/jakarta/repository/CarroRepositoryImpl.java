package org.curso.jakarta.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.curso.jakarta.model.Carro;

import java.util.List;

public class CarroRepositoryImpl implements CarroRepository{
    @PersistenceContext(unitName = "productoJPA")
    EntityManager entityManager;

    @Override
    @Transactional
    public Carro save(Carro c) {
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
    public List<Carro> listAll() {
        return entityManager.createQuery("SELECT c FROM Carro c", Carro.class).getResultList();
    }

    @Override
    public Carro findById(Integer id) {
        return entityManager.find(Carro.class, id);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        entityManager.remove(entityManager.getReference(Carro.class, id));
    }
}
