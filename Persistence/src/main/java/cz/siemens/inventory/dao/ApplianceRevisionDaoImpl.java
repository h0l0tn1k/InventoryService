package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.ApplianceRevision;
import org.springframework.stereotype.Repository;

@Repository
public class ApplianceRevisionDaoImpl extends GenericDao<ApplianceRevision> {

    public ApplianceRevisionDaoImpl() {
        super(ApplianceRevision.class);
    }
}
