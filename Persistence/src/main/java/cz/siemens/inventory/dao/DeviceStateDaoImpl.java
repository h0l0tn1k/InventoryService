package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.DeviceState;
import org.springframework.stereotype.Repository;

@Repository
public class DeviceStateDaoImpl extends GenericDao<DeviceState> {

    public DeviceStateDaoImpl() {
        super(DeviceState.class);
    }
}
