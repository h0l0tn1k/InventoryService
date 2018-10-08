package cz.siemens.inventory.mapper.impl;

import cz.siemens.inventory.entity.ApplianceCalibration;
import cz.siemens.inventory.gen.model.DeviceCalibration;
import cz.siemens.inventory.mapper.DeviceCalibrationMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static cz.siemens.inventory.mapper.DateFormat.YYYY_MM_DD;
import static cz.siemens.inventory.mapper.DateFormat.formatter;

@Service
public class DeviceCalibrationMapperImpl implements DeviceCalibrationMapper {

	@Override
	public ApplianceCalibration mapToInternal(DeviceCalibration object) {
		if(object == null) {
			return null;
		}
		ApplianceCalibration calibration = new ApplianceCalibration();
		calibration.setId(object.getId());
		calibration.setInterval(object.getCalibrationInterval());
		calibration.setLastCalibration(parseStringToDate(object.getLastCalibrationDateString()));
		return calibration;
	}

	@Override
	public DeviceCalibration mapToExternal(ApplianceCalibration object) {
		if(object == null) {
			return null;
		}
		String lastCalibrationDateString = (object.getLastCalibration() == null)
				? "" : object.getLastCalibration().format(DateTimeFormatter.ofPattern(YYYY_MM_DD));

		return new DeviceCalibration().id(object.getId()).calibrationInterval(object.getInterval())
				.lastCalibrationDateString(lastCalibrationDateString);
	}

	private LocalDate parseStringToDate(String dateString) {
		if (dateString == null) {
			return null;
		}
		try {
			return LocalDate.parse(dateString, formatter);
		} catch (DateTimeParseException e) {
			return null;
		}
	}
}
