package cz.siemens.inventory.mapper;

import cz.siemens.inventory.entity.ApplianceRevision;
import cz.siemens.inventory.api.gen.model.DeviceRevision;
import cz.siemens.inventory.api.mapper.DeviceRevisionMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static cz.siemens.inventory.api.mapper.DateFormat.YYYY_MM_DD;
import static cz.siemens.inventory.api.mapper.DateFormat.formatter;

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
				? "" : object.getLastRevision().format(DateTimeFormatter.ofPattern(YYYY_MM_DD));
		return new DeviceRevision().id(object.getId()).revisionInterval(object.getInterval())
				.lastRevisionDateString(lastRevisionDateString);
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
