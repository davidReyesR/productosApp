package org.curso.jakarta.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.curso.jakarta.model.Carro;
import org.curso.jakarta.model.Direccion;

import java.util.List;

public class DireccionRepositoryImpl implements DireccionRepository{
    @PersistenceContext(unitName = "productoJPA")
    EntityManager entityManager;

    @Override
    @Transactional
    public Direccion save(Direccion d) {
        if(d.getId() == null) {
            entityManager.persist(d);
            return d;
        }else{
            if(findById(d.getId())!= null) {
                entityManager.merge(d);
                return d;
            }else{
                return null;
            }
        }
    }

    @Override
    public List<Direccion> listAll() {
        return entityManager.createQuery("SELECT d FROM Direccion d", Direccion.class).getResultList();
    }

    @Override
    public Direccion findById(Integer id) {
        return entityManager.find(Direccion.class, id);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        entityManager.remove(entityManager.getReference(Direccion.class, id));
    }
}
