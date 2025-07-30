package org.curso.jakarta.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.curso.jakarta.model.Producto;

import java.util.List;

public class ProductoRepositoryImpl implements ProductoRepository {
    @PersistenceContext(unitName = "productoJPA")
    EntityManager entityManager;

    @Override
    @Transactional
    public Producto save(Producto p) {
        if(p.getId() == null) {
            entityManager.persist(p);
            return p;
        }else{
            if(findById(p.getId())!= null) {
                entityManager.merge(p);
                return p;
            }else{
                return null;
            }
        }
    }

    @Override
    public List<Producto> listAll() {
        return entityManager.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
    }

    @Override
    public Producto findById(Integer id) {
        return entityManager.find(Producto.class,id);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        entityManager.remove(entityManager.getReference(Producto.class, id));
    }
}
