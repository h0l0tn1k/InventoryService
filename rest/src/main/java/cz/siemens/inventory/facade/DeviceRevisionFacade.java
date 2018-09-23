package cz.siemens.inventory.facade;

import cz.siemens.inventory.gen.model.DeviceRevision;
import cz.siemens.inventory.gen.model.DeviceState;

import java.util.List;
import java.util.Optional;

public interface DeviceRevisionFacade {

	Optional<DeviceRevision> getDeviceRevision(long revisionId);

	DeviceRevision createDeviceRevision(DeviceRevision deviceRevision);
}
