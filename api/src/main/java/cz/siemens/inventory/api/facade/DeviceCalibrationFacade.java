package cz.siemens.inventory.api.facade;

import cz.siemens.inventory.api.gen.model.DeviceCalibration;

import java.util.Optional;

public interface DeviceCalibrationFacade {

	Optional<DeviceCalibration> getDeviceCalibration(long calibrationId);

//	DeviceCalibration createDeviceCalibration(DeviceCalibration deviceCalibration);

	DeviceCalibration updateDeviceCalibration(DeviceCalibration deviceCalibration);
}
