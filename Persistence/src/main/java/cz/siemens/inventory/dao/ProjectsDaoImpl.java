package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.CompanyOwner;
import cz.siemens.inventory.entity.Project;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProjectsDaoImpl extends GenericDao<Project> {

    public ProjectsDaoImpl() {
        super(Project.class);
    }
}
