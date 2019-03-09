package cz.siemens.inventory.facade;

import cz.siemens.inventory.api.facade.DeviceStateFacade;
import cz.siemens.inventory.dao.DeviceStateDao;
import cz.siemens.inventory.entity.DeviceState;
import cz.siemens.inventory.api.mapper.DeviceStateMapper;
import cz.siemens.inventory.mapper.DeviceStateMapperImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class DeviceStateFacadeTest {

	private DeviceStateFacade cut;
	private DeviceStateDao deviceStateDao;
	private DeviceStateMapper deviceStateMapper;
	private cz.siemens.inventory.api.gen.model.DeviceState expectedDeviceStateExternal = getExternalDeviceState(1L,"DeviceState D1");

	@Before
	public void setup() {
		deviceStateDao = Mockito.mock(DeviceStateDao.class);
		deviceStateMapper = new DeviceStateMapperImpl();
		cut = new DeviceStateFacadeImpl(deviceStateDao, deviceStateMapper);
		DeviceState deviceStateInternal = new DeviceState();
		deviceStateInternal.setId(expectedDeviceStateExternal.getId());
		deviceStateInternal.setName(expectedDeviceStateExternal.getName());

		doReturn(Optional.of(deviceStateInternal)).when(deviceStateDao).findById(1L);
		doReturn(new ArrayList<DeviceState>()).when(deviceStateDao).findAll();
	}

	@Test
	public void getDeviceStates_returnsList() {
		List<cz.siemens.inventory.api.gen.model.DeviceState> deviceStates = cut.getDeviceStates();

		assertThat(deviceStates).isEmpty();
	}

	@Test
	public void getDeviceState_byId_returnsOwner() {
		Optional<cz.siemens.inventory.api.gen.model.DeviceState> optionalDeviceState = cut.getDeviceState(1L);

		assertThat(optionalDeviceState.isPresent()).isTrue();
		assertThat(optionalDeviceState).isEqualTo(Optional.of(expectedDeviceStateExternal));
	}

	@Test
	public void createDeviceState_createsOwner() {
		DeviceState expectedDeviceState = new DeviceState();
		expectedDeviceState.setId(4L);
		expectedDeviceState.setName("Company");

		doReturn(expectedDeviceState).when(deviceStateDao).save(expectedDeviceState);

		cz.siemens.inventory.api.gen.model.DeviceState createdDeviceState = cut.createDeviceState(deviceStateMapper.mapToExternal(expectedDeviceState));

		assertThat(createdDeviceState).isNotNull();
	}

	@Test
	public void deleteDeviceState_deletesOwner() {
		Long id = 3L;
		cut.deleteDeviceState(id);

		verify(deviceStateDao).deleteById(id);
	}

	private cz.siemens.inventory.api.gen.model.DeviceState getExternalDeviceState(Long id, String name) {
		return new cz.siemens.inventory.api.gen.model.DeviceState().id(id).name(name);
	}
}
