package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.ApplianceRevision;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class ApplianceRevisionDaoImpl extends GenericDao<ApplianceRevision> {

    public ApplianceRevisionDaoImpl() {
        super(ApplianceRevision.class);
    }
}
