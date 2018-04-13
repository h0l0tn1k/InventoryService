package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.Project;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class ProjectsDaoImpl extends GenericDao<Project> {

    public ProjectsDaoImpl() {
        super(Project.class);
    }
}
