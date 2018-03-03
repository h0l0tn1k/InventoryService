package cz.siemens.inventory.dao;


import cz.siemens.inventory.entity.CompanyOwner;

import java.util.List;

public interface CompanyOwnerDao {

    /**
     * Removes machine an existing machine
     * @param companyOwner
     */
    public void remove(CompanyOwner companyOwner);

    /**
     * Adds a new machine
     * @param companyOwner
     * @return id of created machine
     */
    public Long add(CompanyOwner companyOwner);

    /**
     * Updates an existing machine
     * @param companyOwner
     */
    public void update(CompanyOwner companyOwner);

    /**
     * @return list of all machines
     */
    public List<CompanyOwner> findAll();

    /**
     * @param id
     * @return company owner by Id
     */
    public CompanyOwner findById(Long id);

    /**
     * @param name
     * @return company owner by name
     */
    public CompanyOwner findByName(String name);
}
