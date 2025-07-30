package org.curso.jakarta.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.curso.jakarta.model.Categoria;
import org.curso.jakarta.model.Producto;

import java.util.List;

public class CategoriaRepositoryImpl implements CategoriaRepository{
    @PersistenceContext(unitName = "productoJPA")
    EntityManager entityManager;

    @Override
    @Transactional
    public Categoria save(Categoria c) {
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
    public List<Categoria> listAll() {
        return entityManager.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
    }

    @Override
    public Categoria findById(Integer id) {
        return entityManager.find(Categoria.class, id);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        entityManager.remove(entityManager.getReference(Categoria.class, id));
    }
}
