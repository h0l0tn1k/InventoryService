package cz.siemens.inventory.facade;

import cz.siemens.inventory.gen.model.DeviceCalibration;
import cz.siemens.inventory.gen.model.DeviceRevision;

import java.util.Optional;

public interface DeviceCalibrationFacade {

	Optional<DeviceCalibration> getDeviceCalibration(long calibrationId);

	DeviceCalibration createDeviceCalibration(DeviceCalibration deviceCalibration);
}
