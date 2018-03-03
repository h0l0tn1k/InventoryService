package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.Project;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectsDaoImpl extends GenericDao<Project> {

    public ProjectsDaoImpl() {
        super(Project.class);
    }
}
