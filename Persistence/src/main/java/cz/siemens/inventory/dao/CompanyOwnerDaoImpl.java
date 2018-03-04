package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.CompanyOwner;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class CompanyOwnerDaoImpl extends GenericDao<CompanyOwner> {

    protected CompanyOwnerDaoImpl() {
        super(CompanyOwner.class);
    }
}
