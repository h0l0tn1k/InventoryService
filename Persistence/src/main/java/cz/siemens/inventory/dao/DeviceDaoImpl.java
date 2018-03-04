package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.Device;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class DeviceDaoImpl extends GenericDao<Device> {

    public DeviceDaoImpl() {
        super(Device.class);
    }
}
