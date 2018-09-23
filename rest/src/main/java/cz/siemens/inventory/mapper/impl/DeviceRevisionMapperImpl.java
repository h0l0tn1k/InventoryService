package cz.siemens.inventory.mapper.impl;

import cz.siemens.inventory.entity.ApplianceRevision;
import cz.siemens.inventory.gen.model.DeviceRevision;
import cz.siemens.inventory.mapper.DeviceRevisionMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class DeviceRevisionMapperImpl implements DeviceRevisionMapper {

	@Override
	public ApplianceRevision mapToInternal(DeviceRevision object) {
		if(object == null) {
			return null;
		}
		ApplianceRevision revision = new ApplianceRevision();
		revision.setId(object.getId());
		revision.setInterval(object.getRevisionInterval());
		revision.setLastRevision(parseStringToDate(object.getLastRevisionDateString()));
		return revision;
	}

	@Override
	public DeviceRevision mapToExternal(ApplianceRevision object) {
		if(object == null) {
			return null;
		}
		String lastRevisionDateString = (object.getLastRevision() == null)
				? "" : object.getLastRevision().format(DateTimeFormatter.ISO_DATE);
		return new DeviceRevision().id(object.getId()).revisionInterval(object.getInterval())
				.lastRevisionDateString(lastRevisionDateString);
	}

	private LocalDate parseStringToDate(String dateString) {
		if (dateString == null) {
			return null;
		}
		try {
			return LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);
		} catch (DateTimeParseException e) {
			return null;
		}
	}
}
