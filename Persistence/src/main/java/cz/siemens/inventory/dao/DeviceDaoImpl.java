package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.Device;
import org.springframework.stereotype.Repository;

@Repository
public class DeviceDaoImpl extends GenericDao<Device> {

    public DeviceDaoImpl() {
        super(Device.class);
    }
}
