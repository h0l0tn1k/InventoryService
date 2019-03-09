package cz.siemens.inventory.api.facade;

import cz.siemens.inventory.api.gen.model.DeviceRevision;

import java.util.Optional;

public interface DeviceRevisionFacade {

	Optional<DeviceRevision> getDeviceRevision(long revisionId);

//	DeviceRevision createDeviceRevision(DeviceRevision deviceRevision);

	DeviceRevision updateDeviceRevision(DeviceRevision deviceRevision);
}
