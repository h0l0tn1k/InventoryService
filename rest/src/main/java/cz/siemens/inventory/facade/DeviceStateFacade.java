package cz.siemens.inventory.facade;

import cz.siemens.inventory.gen.model.DeviceState;

import java.util.List;
import java.util.Optional;

public interface DeviceStateFacade {

	List<DeviceState> getDeviceStates();

	Optional<DeviceState> getDeviceState(long deviceStateId);

	DeviceState createDeviceState(DeviceState deviceState);

	void deleteDeviceState(long deviceStateId);
}
