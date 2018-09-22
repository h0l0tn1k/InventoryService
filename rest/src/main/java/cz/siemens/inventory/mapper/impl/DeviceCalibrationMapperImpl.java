package cz.siemens.inventory.mapper.impl;

import cz.siemens.inventory.entity.ApplianceCalibration;
import cz.siemens.inventory.gen.model.DeviceCalibration;
import cz.siemens.inventory.mapper.DeviceCalibrationMapper;
import org.springframework.stereotype.Service;

@Service
public class DeviceCalibrationMapperImpl implements DeviceCalibrationMapper {

	@Override
	public ApplianceCalibration mapToInternal(DeviceCalibration object) {
		ApplianceCalibration calibration = new ApplianceCalibration();
		calibration.setId(object.getId());
		calibration.setInterval(object.getCalibrationInterval());
		//todo: date

		return calibration;
	}

	@Override
	public DeviceCalibration mapToExternal(ApplianceCalibration object) {
		//todo: data
		return new DeviceCalibration().id(object.getId()).calibrationInterval(object.getInterval());
	}
}
