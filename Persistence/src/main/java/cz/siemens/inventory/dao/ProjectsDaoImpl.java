package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.CompanyOwner;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProjectsDaoImpl implements CompanyOwnerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void remove(CompanyOwner companyOwner) {
        CompanyOwner companyO = entityManager.contains(companyOwner) ? companyOwner : entityManager.merge(companyOwner);
        entityManager.remove(companyO);
    }

    @Override
    public Long add(CompanyOwner companyOwner) {
        //todo: validate
        entityManager.persist(companyOwner);
        return companyOwner.getId();
    }

    @Override
    public void update(CompanyOwner companyOwner) {

    }

    @Override
    public List<CompanyOwner> findAll() {
        return entityManager.createQuery("select co from CompanyOwner co", CompanyOwner.class).getResultList();
    }

    @Override
    public CompanyOwner findById(Long id) {
        return entityManager.find(CompanyOwner.class,id);
    }

    @Override
    public CompanyOwner findByName(String name) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Cannot search for null name");
        try {
            return entityManager.createQuery("SELECT co FROM CompanyOwner co where name =:name",
                    CompanyOwner.class).setParameter("name", name).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
