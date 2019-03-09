package cz.siemens.inventory.mapper;

import cz.siemens.inventory.entity.DeviceState;
import cz.siemens.inventory.api.mapper.DeviceStateMapper;
import org.springframework.stereotype.Service;

@Service
public class DeviceStateMapperImpl implements DeviceStateMapper {

	@Override
	public DeviceState mapToInternal(cz.siemens.inventory.api.gen.model.DeviceState object) {
		if(object == null) {
			return null;
		}
		DeviceState deviceState = new DeviceState();
		deviceState.setId(object.getId());
		deviceState.setName(object.getName());
		return deviceState;
	}

	@Override
	public cz.siemens.inventory.api.gen.model.DeviceState mapToExternal(DeviceState object) {
		if(object == null) {
			return null;
		}
		cz.siemens.inventory.api.gen.model.DeviceState deviceState = new cz.siemens.inventory.api.gen.model.DeviceState();
		return deviceState.id(object.getId()).name(object.getName());
	}
}
