package cz.siemens.inventory.dao;


import cz.siemens.inventory.entity.CompanyOwner;

import java.util.List;

public interface CompanyOwnerDao {

    /**
     * Removes an existing company owner
     * @param companyOwner
     */
    public void remove(CompanyOwner companyOwner);

    /**
     * Adds a new company owner
     * @param companyOwner
     * @return id of created company owner
     */
    public Long add(CompanyOwner companyOwner);

    /**
     * Updates an existing company owner
     * @param companyOwner
     */
    public void update(CompanyOwner companyOwner);

    /**
     * @return list of all company owners
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
