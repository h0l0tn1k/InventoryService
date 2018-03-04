package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.DeviceType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class DeviceTypeDaoImpl extends GenericDao<DeviceType> {

    public DeviceTypeDaoImpl() {
        super(DeviceType.class);
    }
}
