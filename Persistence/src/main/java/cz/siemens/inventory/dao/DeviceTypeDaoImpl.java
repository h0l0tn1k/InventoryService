package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.DeviceType;
import org.springframework.stereotype.Repository;

@Repository
public class DeviceTypeDaoImpl extends GenericDao<DeviceType> {

    public DeviceTypeDaoImpl() {
        super(DeviceType.class);
    }
}
