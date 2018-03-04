package cz.siemens.inventory.dao;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@Transactional
public class GenericDao<E extends Serializable> {

    private Class<E> entityClass;

    @PersistenceContext
    protected EntityManager em;

    protected GenericDao(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    public void delete(E entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }

    public void create(E entity) {
        //todo: validate
        em.persist(entity);
    }

    public void update(E entity) {
        em.merge(entity);
    }

    public List<E> readAll() {
        CriteriaQuery<E> criteria = em.getCriteriaBuilder().createQuery(getEntityClass());
        Root<E> entityRoot = criteria.from(getEntityClass());
        criteria.select(entityRoot);

        return em.createQuery(criteria).getResultList();
    }

    public E read(Long id) {
        return em.find(getEntityClass(), id);
    }

    private Class<E> getEntityClass() {
        return entityClass;
    }
}
