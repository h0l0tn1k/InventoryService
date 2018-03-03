package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.Supplier;
import org.springframework.stereotype.Repository;

@Repository
public class SupplierDaoImpl extends GenericDao<Supplier> {

    public SupplierDaoImpl() {
        super(Supplier.class);
    }
}
