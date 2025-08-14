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
        entityManager.persist(p);
        entityManager.flush();
        return p;
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

    @Override
    @Transactional
    public Producto update(Producto p) {
        entityManager.merge(p);
        entityManager.flush();
        return p;
    }
}
