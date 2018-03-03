package cz.siemens.inventory.dao;


import cz.siemens.inventory.entity.Project;

import java.util.List;

public interface ProjectsDao {

    /**
     * Removes machine an existing machine
     * @param companyOwner
     */
    public void remove(Project project);

    /**
     * Adds a new machine
     * @param companyOwner
     * @return id of created machine
     */
    public Long add(Project project);

    /**
     * Updates an existing machine
     * @param companyOwner
     */
    public void update(Project project);

    /**
     * @return list of all Projects
     */
    public List<Project> findAll();

    /**
     * @param id
     * @return Project by Id
     */
    public Project findById(Long id);
}
