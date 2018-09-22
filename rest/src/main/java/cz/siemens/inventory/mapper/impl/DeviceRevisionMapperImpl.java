package cz.siemens.inventory.mapper.impl;

import cz.siemens.inventory.entity.ApplianceRevision;
import cz.siemens.inventory.gen.model.DeviceRevision;
import cz.siemens.inventory.mapper.DeviceRevisionMapper;
import org.springframework.stereotype.Service;

@Service
public class DeviceRevisionMapperImpl implements DeviceRevisionMapper {

	@Override
	public ApplianceRevision mapToInternal(DeviceRevision object) {
		ApplianceRevision revision = new ApplianceRevision();
		revision.setId(object.getId());
		revision.setInterval(object.getRevisionInterval());
		//todo: date

		return revision;
	}

	@Override
	public DeviceRevision mapToExternal(ApplianceRevision object) {
		//todo: data
		return new DeviceRevision().id(object.getId()).revisionInterval(object.getInterval());
	}
}
